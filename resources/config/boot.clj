(use '[caribou.config :only (read-config configure environment)])
(require ' [clojure.java.io :as io])

(def default-config
  {:logging {:loggers [{:type :stdout :level :debug}
                       ;; {:type :remote :host "beast.local" :level :debug}
                       ;; {:type :file :file "caribou-logging.out" :level :warn}
                       ]}
   :debug        true
   :use-database true
   :halo-enabled true
   :halo-prefix "/_halo"
   :halo-key    "replace-with-halo-key"
   :halo-hosts  ["http://127.0.0.1:33333"]
   :database {:classname    "org.h2.Driver"
              :subprotocol  "h2"
              :host         "localhost"
              :database     "caribou_development"
              :user         "h2"
              :password     ""}
   :public-dir     "public"
   :asset-dir      "app/"
   :hooks-ns       "skel.hooks"
   :fields-ns      "skel.fields"
   :migrations-dir "app/migrations"
   :api-public     "public"
   :controller  {:namespace "skel.controllers" :reload true}})

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


