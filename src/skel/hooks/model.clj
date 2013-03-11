(ns skel.hooks.model
    (:require [caribou.model :as model]))

;; a model hook recieves a map as its sole arg, that map contains keys that
;; are partially specific to its lifecycle position

;; generally, a hook should return a potentially modified version of the arg it
;; recieves

;; :content - the thing hooked
;; :model - the relevant model
;; :values - arguments to the function spawning hook - modify this to change
;;           what actually gets stored
;; :op - the operation spawning the hook (mainly used for the save hooks)
;; :opts - optional opts arg to model/create
;; :spec - for create hooks, describes how one would update this row
;; :original - for update hooks, the object before the operation in question

;; creation hooks in order called
:before_save
:before_create
:after_create
:after_save

;; update hooks in order called
:before_save
:before_update
:after_update
:after_save

;; destroy hooks in order called
:before_destroy
:after_destroy

(model/add-hook :model :before_destroy
                ;; the third argument is a unique key, by calling add-hook
                ;; again with a duplicate key, you can replace that hook, by
                ;; providing a unique key you can add an additional hook
                :reinitialize
                (fn [env]
                  (println "this hook never runs")
                  env))

(model/add-hook :model :before_destroy :reinitialize ; replacing the above hook
                (fn [env]
                  (println (:model env) "before_destroy hook, triggered by the"
                           (:op env) "operation")
                  env))
