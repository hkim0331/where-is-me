(ns where-is-me.view
  (:require [ataraxy.response :as response]
            [hiccup.page :refer [html5 include-css]]))

(defn html [& contents]
 [::response/ok
  (html5
   [:head
    [:meta {:charset "utf-8"}]
    [:meta
     {:name "viewport"
      :content "width=device-width ,initial-scale=1,shink-to-fit=no"}]
    (include-css
     "https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css")]
   [:body
    [:div.container-fluid
     contents]])])
