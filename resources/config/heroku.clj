{:logging {:loggers [{:type :stdout :level :warn}]}
 :database {:classname    "org.postgresql.Driver"
            :subprotocol  "postgresql"
            :database     "heroku-db"
            :host         "heroku-host"
            :port         5432
            :user         "heroku-user"
            :password     "heroku-password"
            :ssl          true
            :sslfactory   "org.postgresql.ssl.NonValidatingFactory"}
 :controller {:namespace "skel.controllers" :reload :never}
 :cache-templates :always}
