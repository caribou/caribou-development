(defproject antler/caribou-development "0.1.5"
  :description
  "caribou development: The prototypical caribou project"

  :dependencies
  [[org.clojure/clojure "1.3.0"]
   [antler/lein-caribou "1.4.8"]]

  :sub
   ["admin"
    "api"
    "site"]

  :jvm-opts ["-agentlib:jdwp=transport=dt_socket,server=y,suspend=n"])
