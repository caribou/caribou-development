(defproject antler/caribou-development "0.1.0"
  :description
  "caribou: test site for development purposes"

  :dependencies
  [[antler/caribou-core "0.5.0"]
   [org.clojure/clojure "1.3.0"]]

  :sub
   ["caribou-api"
    "caribou-frontend"
    "caribou-admin"]

  :jvm-opts ["-agentlib:jdwp=transport=dt_socket,server=y,suspend=n"])
