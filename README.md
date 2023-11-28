# AndroidFlavorsDemoNew

New syntax of adding signingConfigs and productFlavors

1. ## signingConfigs
      In gradle.kts file
   
`  signingConfigs {
        create("DEBUG") {
            storeFile = file("${rootDir}/development_keystore.jks")
            storePassword = "123456"
            keyAlias = "development_keystore"
            keyPassword = "123456"
        }
        create("RELEASE") {
            storeFile = file("${rootDir}/production_keystore.jks")
            storePassword = "123456"
            keyAlias = "production_keystore"
            keyPassword = "123456"
        }
    }`

3. ## Add productFlavors

   In gradle.kts file
   
  `  flavorDimensions += "default"
    productFlavors {
        create("dev") {
            applicationIdSuffix = ".dev"
        }
        create("prod") {
            dimension = "default"
        }
    }
    android.applicationVariants.all {
        when (flavorName) {
            "dev" -> {
                buildConfigField("String","BaseURL", project.findProperty("DEV_BASE_URL").toString())
            }
            "prod" -> {
                buildConfigField("String","BaseURL", project.findProperty("PROD_BASE_URL").toString())
            }
        }
    }`


5. ## Add in project: gradle.properties
   
#DEVELOPMENT URL
DEV_BASE_URL="https://dev.example.com"

#PRODUCTION URL
PROD_BASE_URL="https://prod.example.com"
