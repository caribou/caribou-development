(defproject caribou-development "0.12.14"
  :description "The page routing ring handler for caribou"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [ring/ring-jetty-adapter "1.1.8"]
                 [org.immutant/immutant "1.0.0"]
                 [caribou/caribou-frontend "0.12.14"]
                 [caribou/caribou-admin "0.12.16"]
                 [caribou/caribou-api "0.12.14"]
                 [org.clojure/tools.nrepl "0.2.3"]]
  :plugins [[lein-ring "0.8.6"]
            [caribou/lein-caribou "2.4.8"]
            [lein-cljsbuild "0.3.2"]]
  :jvm-opts ["-agentlib:jdwp=transport=dt_socket,server=y,suspend=n" "-Xmx2g"]
  :source-paths ["src"]
  :resource-paths ["resources/"]
  :min-lein-version "2.0.0"
  :migration-namespace skel.migrations
  :main skel.core
  :ring {:handler skel.core/handler
         :init skel.core/init
         :port 33333
         :auto-reload? false
         :servlet-name "caribou-development-frontend"}
  :immutant {:context-path "/"
             :init skel.immutant/init}
  :cljsbuild {:repl-listen-port 44994
              :builds
              [{:source-paths ["resources/cljs"]
                :compiler {:output-to "resources/public/js/app/skel.js"
                           :optimizations :whitespace
                           :pretty-print true}}]})
