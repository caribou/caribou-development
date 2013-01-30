(defproject caribou-development-admin "0.1.0"
  :description "Flexible and adaptive admin for caribou-api"
  :url "http://github.com/antler/caribou-admin"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [antler/caribou-admin "0.7.6"]]
  :source-paths ["src" "../src"]
  :resource-paths ["resources/" "../resources/"]            
  :ring {:handler caribou.admin.core/app
         :servlet-name "caribou-development-admin"
         :join? false
         :init caribou.admin.core/init
         :port 33553})

