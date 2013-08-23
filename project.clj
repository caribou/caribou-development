(defproject caribou-development "0.12.4"
  :description "The page routing ring handler for caribou"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [ring/ring-jetty-adapter "1.1.8"]
                 [org.immutant/immutant "1.0.0"]
                 [antler/caribou-frontend "0.12.4"]
                 [antler/caribou-admin "0.12.4"]
                 [antler/caribou-api "0.12.4"]
                 [org.clojure/tools.nrepl "0.2.3"]]
  :plugins [[lein-ring "0.8.6"]
            [antler/lein-caribou "2.4.4"]]
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
             :init skel.immutant/init})
