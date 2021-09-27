(ns where-is-me.boundary.locations
  (:require
   [ataraxy.response :as response]
   [clojure.java.jdbc :as jdbc]
   [clojure.string :refer [starts-with?]]
   [duct.database.sql]
   [integrant.core :as ig]))


(defprotocol Locations
 (create-loc [db loc])
 (find-loc [db])
 (list-loc [db pat])
 (lists-loc [db]))

(extend-protocol Locations
  duct.database.sql.Boundary
  (create-loc [{db :spec} loc]
          (let [ret (jdbc/insert! db :locations {:location loc})]
            (-> ret first)))

  (find-loc [{db :spec}]
        (let [ret (jdbc/query db ["select * from locations order by id desc"])]
          (-> ret first)))

  ;; FIXME: want to use `like ?%`
  (list-loc [{db :spec} pat]
        (let [ret (jdbc/query db ["select * from locations order by id"])]
          (->> ret
               (filter #(starts-with? (:timestamp %) pat)))))

  (lists-loc [{db :spec}]
         (let [ret (jdbc/query db ["select * from locations order by id"])]
           ret)))
