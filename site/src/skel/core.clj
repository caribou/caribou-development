(ns skel.core
  (:use [ring.middleware.json-params :only (wrap-json-params)]
        [ring.middleware.multipart-params :only (wrap-multipart-params)]
        [ring.middleware.params :only (wrap-params)]
        [ring.middleware.file :only (wrap-file)]
        [ring.middleware.head :only (wrap-head)]
        [ring.middleware.file-info :only (wrap-file-info)]
        [ring.middleware.resource :only (wrap-resource)]
        [ring.middleware.nested-params :only (wrap-nested-params)]
        [ring.middleware.keyword-params :only (wrap-keyword-params)]
        [ring.middleware.reload :only (wrap-reload)]
        [ring.middleware.session :only (wrap-session)]
        [ring.middleware.session.cookie :only (cookie-store)]
        [ring.middleware.cookies :only (wrap-cookies)]
        [ring.middleware.content-type :only (wrap-content-type)])
  (:require [swank.swank :as swank]
            [lichen.core :as lichen]
            [caribou.config :as config]
            [caribou.db :as db]
            [caribou.model :as model]
            [caribou.logger :as log]
            [caribou.app.i18n :as i18n]
            [caribou.app.pages :as pages]
            [caribou.app.template :as template]
            [caribou.app.halo :as halo]
            [caribou.app.middleware :as middleware]
            [caribou.app.request :as request]
            [caribou.app.helpers :as helpers]
            [caribou.admin.routes :as admin-routes]
            [caribou.admin.core :as admin-core]
            [caribou.app.handler :as handler]))

(declare handler)

(defn provide-helpers
  [handler]
  (fn [request]
    (let [request (merge request helpers/helpers)]
      (handler request))))

(defn reload-pages
  []
  (pages/create-page-routes
   (model/arrange-tree
    (model/db
     #(model/gather :page {:include {:siphons {}}}))))
  (pages/add-page-routes
   admin-routes/admin-routes
   'caribou.admin.controllers
   "/_admin"
   admin-core/admin-wrapper))

(defn init
  []
  (config/init)
  (model/init)
  (i18n/init)
  (template/init)
  (reload-pages)
  (halo/init
   {:reload-pages reload-pages
    :halo-reset handler/reset-handler})

  (def handler
    (-> (handler/gen-handler)
        (provide-helpers)
        (wrap-reload)
        (wrap-file (@config/app :asset-dir))
        (wrap-resource (@config/app :public-dir))
        (wrap-file-info)
        (wrap-head)
        (lichen/wrap-lichen (@config/app :asset-dir))
        (middleware/wrap-servlet-path-info)
        (middleware/wrap-xhr-request)
        (request/wrap-request-map)
        (wrap-json-params)
        (wrap-multipart-params)
        (wrap-keyword-params)
        (wrap-nested-params)
        (wrap-params)
        (db/wrap-db @config/db)
        (wrap-content-type)
        (wrap-session)
        (wrap-cookies)))

  (swank/start-server :host "127.0.0.1" :port 4010))
