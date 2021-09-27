(ns where-is-me.handler.core
  (:require [ataraxy.core :as ataraxy]
            [ataraxy.response :as response]
            [integrant.core :as ig]))

(defmethod ig/init-key :where-is-me.handler.core/create [_ options]
  (fn [{[_ {:keys [loc]}] :ataraxy/result}]
    [::response/ok {:core "create"
                    :loc loc}]))

(defmethod ig/init-key :where-is-me.handler.core/find [_ options]
  (fn [_]
    [::response/ok {:core "find"}]))

(defmethod ig/init-key :where-is-me.handler.core/list [_ options]
  (fn [{[_ params] :ataraxy/result}]
    [::response/ok {:core "list"
                    :params params}]))

(defmethod ig/init-key :where-is-me.handler.core/lists [_ options]
  (fn [_]
    [::response/ok {:core "lists"}]))
