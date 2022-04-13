(ns where-is-me.handler.core
  (:require
   [ataraxy.response :as response]
   [integrant.core :as ig]

   [environ.core :refer [env]]
   [hiccup.form :refer [form-to submit-button
                        password-field hidden-field text-field]]
   [ring.util.anti-forgery :refer [anti-forgery-field]]
   [where-is-me.boundary.locations :as locs]
   [where-is-me.view :as view]))

(def ^:private version "0.3.1")

;; display usage as html?
(defmethod ig/init-key :where-is-me.handler.core/help [_ _]
  (fn [_]
    [::response/ok (str "version:" version)]))

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
        [:h4 "hkimura は今、"]
        [:div (shorten timestamp) ", " location]
        [:hr]
        [:div "w.hkim.jp"]))))

(defmethod ig/init-key :where-is-me.handler.core/create [_ {:keys [db]}]
  (fn [{:as req [_ loc] :ataraxy/result}]
    (try
      (when (= (or (env :auth-token) "zzz")
               (get-in req [:headers "auth-token"]))
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

(defmethod ig/init-key :where-is-me.handler.core/l [_ _]
  (fn [_]
    (view/html
     [:h2 "w.hkim.jp/l"]
     (form-to [:post "/l"]
              (anti-forgery-field)
              [:div {:class "row align-items-first"}
               [:div {:class "col-1"} "location:"]
               [:div {:class "col-2"} (text-field "loc")]]
              [:div {:class "row align-items-first"}
               [:div {:class "col-1"} "secret:"]
               [:div {:class "col-2"} (password-field "secret")]]
              [:div (submit-button "update")]))))

(defmethod ig/init-key :where-is-me.handler.core/l-post [_ {:keys [db]}]
  (fn [{{:keys [secret loc]} :params}]
    (if (= secret (env :w-i-m))
      (do
        (locs/create-loc db loc)
        [::response/found "/"])
      (view/html
       [:h2 "w.hkim.jp/l"]
       [:p {:style {:color "red"}} "secret does not match"]))))
