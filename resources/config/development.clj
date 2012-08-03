{:logging {:log-pattern  "%-5p %u [%t]: %m %F %L%n"
           :log-level    :debug}
 :database {:classname    "org.h2.Driver"
            :subprotocol  "h2"
            :host         "localhost"
            :database     "caribou_development"
            :user         "h2"
            :password     ""} 
 :controller-ns  "skel.controllers"}
