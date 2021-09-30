(ns where-is-me.handler.core
  (:require [ataraxy.response :as response]
            [integrant.core :as ig]
            ;;
            [where-is-me.boundary.locations :as locs]
            [where-is-me.view :as view]))

(def ^:private version "0.2.3")

(defmethod ig/init-key :where-is-me.handler.core/version [_ _]
  (fn [_]
    [::response/ok {:version version}]))

;; lazy-seq returns
;; (defn- shorten [ts]
;;   (let [[time date] (clojure.string/split ts #"\s")]
;;     (str (drop 5 time)
;;          " "
;;          (take 5 date))))

;; FIXME: dirty
(defn- shorten [ts]
 (str (subs ts 5 7) "/" (subs ts 8 10) " " (subs ts 11 16)))

(defmethod ig/init-key :where-is-me.handler.core/html [_ {:keys [db]}]
  (fn [_]
    (let [{:keys [timestamp location]} (locs/find-loc db)]
       (view/html
        [:h4 "himura は今、"]
        [:div (shorten timestamp) ", " location]
        [:hr]
        [:div "w.hkim.jp"]))))

(defmethod ig/init-key :where-is-me.handler.core/create [_ {:keys [db]}]
  (fn [{:as req [_ loc] :ataraxy/result}]
    (try
      (when (= "secret" (get-in req [:headers "auth-token"]))
        [::response/ok (locs/create-loc db loc)])
      (catch Exception e
        [::response/unauthorized (.getMessage e)]))))

(defmethod ig/init-key :where-is-me.handler.core/find [_ {:keys [db]}]
  (fn [_]
    [::response/ok (locs/find-loc db)]))

(defmethod ig/init-key :where-is-me.handler.core/list [_ {:keys [db]}]
  (fn [{[_ params] :ataraxy/result}]
    [::response/ok (locs/list-loc db params)]))

(defmethod ig/init-key :where-is-me.handler.core/lists [_ {:keys [db]}]
  (fn [_]
    [::response/ok (locs/lists-loc db)]))
