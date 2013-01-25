{:logging {:loggers [{:type :stdout :level :debug}
                     ;; {:type :remote :host "beast.local" :level :debug}
                     ;; {:type :file :file "caribou-logging.out" :level :debug}
                     ]}
 :database {:classname    "org.postgresql.Driver" ;; "com.mysql.jdbc.Driver" ;; "org.h2.Driver"
            :subprotocol  "postgresql" ;; "mysql" ;; "h2"
            :host         "localhost" ;; "127.0.0.1:3306" ;; "localhost"
            :database     "caribou_development"
            :user         "postgres" ;; "root" ;; "h2"
            :password     "" } ;; "1nt3rfac3"} ;; ""
 :controller-ns  "skel.controllers"}
