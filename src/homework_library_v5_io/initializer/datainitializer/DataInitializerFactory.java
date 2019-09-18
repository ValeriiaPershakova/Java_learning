package homework_library_v5_io.initializer.datainitializer;

import homework_library_v5_io.initializer.serviceinitializer.ServicesHolder;

public final class DataInitializerFactory {
    private DataInitializerFactory() {
    }
     public static BasicDataInitializer getDataInitializer(DataInitializerType dataInitializerType, ServicesHolder servicesHolder) {
         switch (dataInitializerType) {
             case IN_MEMORY: {
                 return new InMemoryInitializer(servicesHolder);
             }
             case FROM_TXT_FILE:{
                 return new TXTFileInitializer(servicesHolder);
             }
             case FROM_XML_FILE_SMALL:{
                return new XMLSmallFileInitializer(servicesHolder);
             }
             case FROM_XML_FILE_BIG:{
                 return new XMLBiglFileInitializer(servicesHolder);
             }
             default:{
                 throw  new RuntimeException("Unknown initilizer for '" + dataInitializerType+ "'");
             }
         }
     }
}
