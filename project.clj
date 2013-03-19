(defproject antler/caribou-development "0.9.2"
  :description
  "caribou development: The prototypical caribou project"

  :dependencies
  [[org.clojure/clojure "1.4.0"]
   [antler/caribou-core "0.9.7"]]

  :migration-namespace "skel.migrations"

  :sub
   ["api"
    "site"]

  :jvm-opts ["-agentlib:jdwp=transport=dt_socket,server=y,suspend=n"])
