(defproject caribou-devsite "0.11.0"
  :description "The page routing ring handler for caribou"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [ring/ring-jetty-adapter "1.1.8"]
                 [org.immutant/immutant "0.10.0"]
                 [antler/caribou-frontend "0.11.32"]
                 [antler/caribou-admin "0.11.36"]
                 [antler/caribou-api "0.11.26"]
                 [org.clojure/tools.nrepl "0.2.3"]]
  :dev-dependencies [[lein-ring "0.8.2"]]
  :jvm-opts ["-agentlib:jdwp=transport=dt_socket,server=y,suspend=n"]
  :source-paths ["src"]
  :resource-paths ["resources/"]
  :migration-namespace skel.migrations
  :immutant {:context-path "/"}
  :min-lein-version "2.0.0"
  :main skel.core
  :ring {:handler skel.core/handler
         :servlet-name "caribou-development-frontend"
         :init skel.core/init
         :port 33333})
