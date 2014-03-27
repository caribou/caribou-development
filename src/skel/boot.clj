(ns skel.boot
  (:require [caribou.core :as caribou]
            [caribou.config :as config]
            [caribou.app.config :as app-config]
            [caribou.plugin.state :as plug-state]
            [caribou.plugin.c3p0 :as c3p0-plugin]))

(defn integrate-plugins
  [config]
  (let [plugins (plug-state/new)
        c3p0-plugin (c3p0-plugin/create)
        plugins (reduce plug-state/register
                        plugins
                        [c3p0-plugin])
        state (plug-state/init plugins config)]
    state))

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
          :reload false
          :options {:output-to "resources/public/js/app/skel.js"
                    :output-dir "resources/public/js/app/out"
                    :pretty-print true}
          :brepl {:listen false
                  :port 44994
                  :path "repl"}}
   :controller {:namespace "skel.controllers"
                :reload true}
   :database {:classname    "org.postgresql.Driver"
              :subprotocol  "postgresql"
              :host         "localhost"
              :database     "skel_production"
              :user         "postgres_user"
              :password     "postgres_password"}
   :error {:show-stacktrace false
           :catch-exceptions true}
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
  (let [config (-> (app-config/default-config)
                   (config/merge-config local-config)
                   config/config-from-environment
                   (config/merge-db-connection {:connection "DATABASE_URL"})
                   config/process-config
                   caribou/init)
        plugged (integrate-plugins config)]
    (:config plugged)))
