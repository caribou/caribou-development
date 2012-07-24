(use '[caribou.config :only (read-config configure environment)])
(require ' [clojure.java.io :as io])

(def default-config
  {:logging {:log-pattern  "\n%p %m (%x) %n\n"
             :log-level    :debug
             :log-filter   (constantly true)}
   :debug        true
   :use-database true
   :halo-enabled true
   :halo-prefix "/_halo"
   :halo-key    "replace-with-halo-key"
   :halo-host   "http://127.0.0.1:33333"
   :database {:classname    "org.h2.Driver"
              :subprotocol  "h2"
              :host         "localhost"
              :database     "caribou_development"
              :user         "h2"
              :password     ""}
   :public-dir     "public"
   :asset-dir      "../app/"
   :hooks-dir      "../app/hooks"
   :migrations-dir "../app/migrations"
   :api-public     "public"
   :controller-ns  "skel.controllers"})

(defn submerge
  [a b]
  (if (string? b) b (merge a b)))

(defn get-config
  "Loads the appropritate configuration file based on environment"
  []
  (let [config-file (format "config/%s.clj" (name (environment)))]
    (println "Loading Caribou config " config-file)
    (merge-with submerge default-config (read-config (io/resource config-file)))))

;; This call is required by Caribou
(configure (get-config))


