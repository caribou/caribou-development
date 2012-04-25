(use '[caribou.config :only (configure)])

(def default-config
  {
    :debug        true
    :use-database true
    :halo-enabled true
    :halo-prefix "/_halo"
    :halo-key    "replace-with-halo-key"
    :halo-host   "http://localhost:33333"
    :database {
        :classname    "org.postgresql.Driver"
        :subprotocol  "postgresql"
        :host         "localhost"
        :database     "caribou_development"
        :user         "postgres"
        ;:password    "password"
    }
    :template-dir   "resources/templates" 
    :controller-ns  "skel.controllers"
    :public-dir     "resources/public"
  })

(defn get-config
  []
  default-config)

;; This call is required by Caribou
(configure (get-config))
