(ns where-is-me.handler.core
  (:require [ataraxy.core :as ataraxy]
            [ataraxy.response :as response]
            [integrant.core :as ig]
            ;;
            [clojure.string :as str]
            [where-is-me.boundary.locations :as locs]
            [where-is-me.view :as view]))

(def ^:private version "0.2.2")

(defmethod ig/init-key :where-is-me.handler.core/version [_ _]
  (fn [_]
    [::response/ok {:version version}]))

(defn- shorten [ts]
  (let [[time date] (str/split ts #"\s")]
    (apply str (concat (drop 5 time) " " (take 5 date)))))

(defmethod ig/init-key :where-is-me.handler.core/html [_ {:keys [db]}]
  (fn [_]
    (let [{:keys [timestamp location]} (locs/find-loc db)]
       (view/html
        [:h4 "himura は今、"]
        [:div (shorten timestamp) ", " location]
        [:hr]
        [:div "w.hkim.jp"]))))

(defmethod ig/init-key :where-is-me.handler.core/create [_ {:keys [db]}]
  (fn [{[_ loc] :ataraxy/result}]
    [::response/ok (locs/create-loc db loc)]))

(defmethod ig/init-key :where-is-me.handler.core/find [_ {:keys [db]}]
  (fn [_]
    [::response/ok (locs/find-loc db)]))

(defmethod ig/init-key :where-is-me.handler.core/list [_ {:keys [db]}]
  (fn [{[_ params] :ataraxy/result}]
    [::response/ok (locs/list-loc db params)]))

(defmethod ig/init-key :where-is-me.handler.core/lists [_ {:keys [db]}]
  (fn [_]
    [::response/ok (locs/lists-loc db)]))
