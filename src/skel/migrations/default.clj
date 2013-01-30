(ns $project$.migrations.default
  (:require [caribou.model :as model]))

(defn migrate
  []
  (model/invoke-models)
  (model/create :page {:name "Home" :path "" :controller "home" :action "home" :template "home.html"}))

(defn rollback
  [] 
  (if-let [default-page (model/pick :page {:where {:name "Home"}})]
    (model/destroy :page (:id default-page))))
