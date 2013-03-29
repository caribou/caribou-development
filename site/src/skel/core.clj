(ns skel.core
  (:use [ring.middleware.json-params :only (wrap-json-params)]
        [ring.middleware.multipart-params :only (wrap-multipart-params)]
        [ring.middleware.reload :only (wrap-reload)]
        [ring.middleware.session :only (wrap-session)]
        [ring.middleware.cookies :only (wrap-cookies)]
        [ring.middleware.content-type :only (wrap-content-type)])
  (:require [compojure.handler :as compojure]
            [swank.swank :as swank]
            [caribou.config :as config]
            [caribou.db :as db]
            [caribou.model :as model]
            [caribou.app.i18n :as i18n]
            [caribou.app.pages :as pages]
            [caribou.app.template :as template]
            [caribou.app.halo :as halo]
            [caribou.app.middleware :as middleware]
            [caribou.app.request :as request]
            [caribou.admin.routes :as admin-routes]
            [caribou.admin.core :as admin-core]
            [caribou.app.handler :as handler]))

(declare handler)

(defn reload-pages
  []
  (pages/create-page-routes
   (model/arrange-tree
    (model/db
     #(model/gather :page {:include {:siphons {}}}))))
  (pages/add-page-routes admin-routes/admin-routes 'caribou.admin.controllers "/_admin" admin-core/admin-wrapper))

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
    (-> (handler/handler)
        (wrap-reload)
        (handler/use-public-wrapper (@config/app :public-dir))
        (middleware/wrap-servlet-path-info)
        (request/wrap-request-map)
        (wrap-json-params)
        (wrap-multipart-params)
        (db/wrap-db @config/db)
        (compojure/api)
        (wrap-content-type)
        (wrap-session)
        (wrap-cookies)))

  (swank/start-server :host "127.0.0.1" :port 4010))
