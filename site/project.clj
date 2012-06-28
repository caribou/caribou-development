(defproject caribou-development "0.1.0-SNAPSHOT"
  :description "The prototypical caribou project frontend"
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [antler/caribou-frontend "0.5.5"]]
  :jvm-opts ["-agentlib:jdwp=transport=dt_socket,server=y,suspend=n"]
  :resource-paths ["resources/" "../resources/"]            
  :ring {:handler skel.core/handler
         :servlet-name "caribou-development-frontend"
         :init skel.core/init
         :port 33333})
