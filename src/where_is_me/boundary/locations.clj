(ns where-is-me.boundary.locations
  (:require
   [ataraxy.response :as response]
   [clojure.java.jdbc :as jdbc]
   [duct.database.sql]
   [integrant.core :as ig]))

(defn now []
  (.format (java.text.SimpleDateFormat. "MM/dd HH:mm") (java.util.Date.)))

(defprotocol Locations
 (create [db loc])
 (find [db])
 (list [db pat])
 (lists [db]))

(extend-protocol Locations
  duct.database.sql.Boundary
  (create [{db :spec} loc]
    (let [ret (jdbc/insert! db :locations {:location loc})]
      (-> ret first)))
  (find [{db :spec}]
    (let [ret (jdbc/query db ["select * from locations order by id desc"])]
     (-> ret first)))
  (list [{db :spec} pat]
   (let [ret (jdbc/query
              db
              ["select * from locations where timestamp like ?"]
              (str "%" pat "%"))]
     ret))
  (lists [{db :spec}]
   (let [ret (jdbc/query db ["select * from locations order by id"])]
    ret)))
