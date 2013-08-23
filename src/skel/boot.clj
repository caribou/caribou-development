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
          :options {:output-dir "resources/public/js/out"
                    :pretty-print true}}
   :controller {:namespace "{project}.controllers"
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
   :field {:namespace "{project}.fields"
           :slug-transform [[#"['\"]+" ""]
                            [#"[_ \\/?%:#^\[\]<>@!|$&*+;,.()]+" "-"]
                            [#"^-+|-+$" ""]]}
   :hooks {:namespace "{project}.hooks"}
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
