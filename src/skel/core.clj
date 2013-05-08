(ns skel.core
  (:require [ring.middleware
             [json-params :refer (wrap-json-params)]
             [multipart-params :refer (wrap-multipart-params)]
             [params :refer (wrap-params)]
             [file :refer (wrap-file)]
             [head :refer (wrap-head)]
             [file-info :refer (wrap-file-info)]
             [resource :refer (wrap-resource)]
             [nested-params :refer (wrap-nested-params)]
             [keyword-params :refer (wrap-keyword-params)]
             [reload :refer (wrap-reload)]
             [session :refer (wrap-session)]
             [cookies :refer (wrap-cookies)]
             [content-type :refer (wrap-content-type)]]
            [ring.middleware.session.cookie :refer (cookie-store)]
            [swank.swank :as swank]
            [lichen.core :as lichen]
            [caribou
             [config :as config]
             [db :as db]
             [model :as model]
             [logger :as log]
             [core :as caribou]]
            [caribou.app
             [i18n :as i18n]
             [pages :as pages]
             [template :as template]
             [middleware :as middleware]
             [request :as request]
             [helpers :as helpers]
             [handler :as handler]]
            [caribou.admin
             [routes :as admin-routes]
             [core :as admin-core]]
            [caribou.api
             [routes :as api-routes]
             [core :as api-core]]
            [skel.boot :as boot]))

(declare handler)

(defn provide-helpers
  [handler]
  (fn [request]
    (let [request (merge request helpers/helpers)]
      (handler request))))

(defn reload-pages
  []
  (pages/add-page-routes
   (pages/all-pages)
   (config/draw :controller :namespace))

  (pages/add-page-routes
   admin-routes/admin-routes
   'caribou.admin.controllers
   "/_admin"
   admin-core/admin-wrapper)

  (pages/add-page-routes
   api-routes/api-routes
   'caribou.api.controllers
   "/_api"
   api-core/api-wrapper))

(defn init
  []
  (let [config (boot/boot)]
    (caribou/with-caribou config
      (reload-pages)
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
            (handler/wrap-caribou config)
            (wrap-session)
            (wrap-cookies))))))