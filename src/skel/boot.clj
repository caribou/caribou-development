(ns skel.boot
  (:require [caribou.core :as caribou]
            [caribou.config :as config]
            [caribou.app.config :as app-config]))

(def local-config
  {:app {:use-database true
         :public-dir "public"
         :default-locale "global"
         :localize-routes ""}
   :assets {:dir "app/"
            :prefix nil
            :root ""}
   :aws {:bucket nil
         :credentials nil}
   :cljs {:root "resources/cljs"
          :reload true
          :options {:output-to "resources/public/js/app/skel.js"
                    :output-dir "resources/public/js/app/out"
                    :pretty-print true}
          :brepl {:listen true
                  :port 44994
                  :path "repl"}}
   :controller {:namespace "skel.controllers"
                :reload true}
   :database {:classname    "org.h2.Driver"
              :subprotocol  "h2"
              :host         "localhost"
              :protocol     "file"
              :path         "/tmp/"
              :database     "taiga_development"
              :user         "h2"
              :password     ""}
   :error {:show-stacktrace false}
   :field {:namespace "skel.fields"
           :slug-transform [[#"['\"]+" ""]
                            [#"[_ \\/?%:#^\[\]<>@!|$&*+;,.()]+" "-"]
                            [#"^-+|-+$" ""]]}
   :hooks {:namespace "skel.hooks"}
   :index {:path "caribou-index"
           :default-limit 1000}
   :logging {:loggers [{:type :stdout :level :debug}]}
   :nrepl {:port nil}
   :query {:enable-query-cache  false
           :query-defaults {}}
   :template {:cache-strategy :never}})

(defn boot
  []
  (let [default (app-config/default-config)
        local (config/merge-config default local-config)
        config (config/config-from-environment local)]
    (caribou/init config)))
