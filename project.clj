(defproject antler/caribou-development "0.1.0"
  :description
  "caribou: test site for development purposes"

  :dependencies
  [[antler/caribou-core "0.5.0"]
   [org.clojure/clojure "1.3.0"]
   [antler/lein-caribou "1.2.8"]]

  :sub
   ["admin"
    "api"
    "site"]

  :jvm-opts ["-agentlib:jdwp=transport=dt_socket,server=y,suspend=n"])
