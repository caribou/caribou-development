(ns skel.core
  (:use [ring.middleware.json :only (wrap-json-params)]
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
  (:require [ring.adapter.jetty :as jetty]
            [lichen.core :as lichen]
            [caribou.config :as config]
            [caribou.db :as db]
            [caribou.model :as model]
            [caribou.logger :as log]
            [caribou.repl :as repl]
            [caribou.core :as caribou]
            [caribou.app.i18n :as i18n]
            [caribou.app.pages :as pages]
            [caribou.app.template :as template]
            [caribou.app.halo :as halo]
            [caribou.app.middleware :as middleware]
            [caribou.app.request :as request]
            [caribou.app.helpers :as helpers]
            [caribou.app.cljs :as cljs]
            [caribou.admin.routes :as admin-routes]
            [caribou.admin.core :as admin-core]
            [caribou.api.routes :as api-routes]
            [caribou.api.core :as api-core]
            [caribou.app.handler :as handler]
            [skel.boot :as boot]
            [skel.routes :as routes]))

(declare handler)

(defn provide-helpers
  [handler]
  (fn [request]
    (let [request (merge request helpers/helpers)]
      (handler request))))

(defn reload-pages
  []
  (pages/add-page-routes
   admin-routes/admin-routes
   'caribou.admin.controllers
   "/_admin"
   admin-core/admin-wrapper)

  (pages/add-page-routes
   api-routes/api-routes
   'caribou.api.controllers
   "/_api"
   api-core/api-wrapper)

  (pages/add-page-routes
   (routes/gather-pages)
   (config/draw :controller :namespace)))

(defn init
  []
  (let [config (boot/boot)]
    (caribou/with-caribou config
      (reload-pages)
      (repl/repl-init)
      (def handler
        (-> (handler/handler reload-pages)
            (provide-helpers)
            (wrap-reload)
            (wrap-file (config/draw :assets :dir))
            (wrap-resource (config/draw :app :public-dir))
            (wrap-file-info)
            (wrap-head)
            (lichen/wrap-lichen (config/draw :assets :dir))
            (middleware/wrap-servlet-path-info)
            (middleware/wrap-xhr-request)
            (request/wrap-request-map)
            (wrap-json-params)
            (wrap-multipart-params)
            (wrap-keyword-params)
            (wrap-nested-params)
            (wrap-params)
            (wrap-content-type)
            (cljs/wrap-cljs)
            (handler/wrap-caribou config)
            (wrap-session)
            (wrap-cookies))))))

;; ^^^^^^^^^^
;; for heroku
;; vvvvvvvvvv
(defn -main [& [port]]
  (init)
  (let [port (Integer. (or port (System/getenv "PORT") 33333))]
    (jetty/run-jetty #'handler {:port port})))

