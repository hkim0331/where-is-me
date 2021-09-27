(ns where-is-me.handler.core
  (:require [ataraxy.core :as ataraxy]
            [ataraxy.response :as response]
            [integrant.core :as ig]
            ;;
            [where-is-me.boundary.locations :as locs]))

(def ^:private version "0.2.1")

(defmethod ig/init-key :where-is-me.handler.core/version [_ _]
  (fn [_]
    [::response/ok {:version version}]))

(defmethod ig/init-key :where-is-me.handler.core/html [_ {:keys [db]}]
  (fn [_]
    (let [{:keys [location timestamp]} (locs/find-loc db)]
      [::response/ok (str "<h2>hkimura は今、</h2>"
                          "<p>" timestamp ", " location "</p>")])))

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
