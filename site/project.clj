(defproject caribou-devsite "0.1.0-SNAPSHOT"
  :description "The page routing ring handler for caribou"
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [antler/caribou-frontend "0.3.4"]]
  :jvm-opts ["-agentlib:jdwp=transport=dt_socket,server=y,suspend=n"]
  :resource-paths ["resources/" "../resources/"]            
  :ring {:handler skel.core/handler
         :servlet-name "caribou-development-frontend"
         :init skel.core/init
         :port 33333})
