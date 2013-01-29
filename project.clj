(defproject antler/caribou-development "0.1.10"
  :description
  "caribou development: The prototypical caribou project"

  :dependencies
  [[org.clojure/clojure "1.3.0"]
   [antler/caribou-core "0.7.26"]]

  :migration-namespace "skel.migrations"

  :sub
   ["admin"
    "api"
    "site"]

  :jvm-opts ["-agentlib:jdwp=transport=dt_socket,server=y,suspend=n"])
