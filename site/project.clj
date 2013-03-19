(defproject caribou-devsite "0.1.0"
  :description "The page routing ring handler for caribou"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [antler/caribou-frontend "0.9.15"]
                 [antler/caribou-admin "0.9.8"]
                 [swank-clojure "1.4.2"]]
  :jvm-opts ["-agentlib:jdwp=transport=dt_socket,server=y,suspend=n"]
  :source-paths ["src" "../src"]
  :resource-paths ["resources/" "../resources/"]            
  :ring {:handler skel.core/handler
         :servlet-name "caribou-development-frontend"
         :init skel.core/init
         :port 33333})
