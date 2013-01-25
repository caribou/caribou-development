(defproject antler/caribou-development "0.1.9"
  :description
  "caribou development: The prototypical caribou project"

  :dependencies
  [[org.clojure/clojure "1.3.0"]
   [antler/caribou-core "0.7.23"]]

  :migration-namespace "$project$.migrations"

  :sub
   ["admin"
    "api"
    "site"]

  :jvm-opts ["-agentlib:jdwp=transport=dt_socket,server=y,suspend=n"])
