(ns where-is-me.handler.core
  (:require [ataraxy.core :as ataraxy]
            [ataraxy.response :as response]
            [integrant.core :as ig]
            ;;
            [where-is-me.boundary.locations :as locs]))

(defmethod ig/init-key :where-is-me.handler.core/create [_ {:keys [db]}]
  (fn [{[_ loc] :ataraxy/result}]
   [::response/ok (locs/create db loc)]))


(defmethod ig/init-key :where-is-me.handler.core/find [_ {:keys [db]}]
  (fn [_]
    [::response/ok (locs/find db)]))

(defmethod ig/init-key :where-is-me.handler.core/list [_ {:keys [db]}]
  (fn [{[_ params] :ataraxy/result}]
    [::response/ok (locs/list db params)]))

(defmethod ig/init-key :where-is-me.handler.core/lists [_ {:keys [db]}]
  (fn [_]
    [::response/ok (locs/lists db)]))
