{:logging {:log-pattern "%d %p %m%n"
           :log-level :warn}
 :database {:classname    "org.postgresql.Driver"
            :subprotocol  "postgresql"
            :host         "localhost"
            :database     "caribou_production"
            :user         "postgres"
            :password     "postgres"}
 :asset-dir      "assets"}
