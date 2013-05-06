(ns skel.boot
  (:require [caribou.core :as caribou]
            [caribou.config :as config]
            [caribou.app.config :as app-config]))

(defn boot
  []
  (let [default (app-config/default-config)
        config (config/config-from-environment default)]
    (caribou/init config)))