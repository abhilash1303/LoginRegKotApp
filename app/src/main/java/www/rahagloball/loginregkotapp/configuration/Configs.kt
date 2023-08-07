package www.rahagloball.loginregkotapp.configuration

object Configs {
    const val BASE_URL2 = "https://nationlearns.com/api/"
    const val BASE_URL21 = "https://bucket-nationlearns.s3.ap-south-1.amazonaws.com/"

    const val VID_CATGRY_URL = BASE_URL2 + "video-categories"
    const val KEY_VIDCTRGYNAME = "cat_name"

    const val KEY_VIDCTRGYID = "id"
    const val JSON_VIDCTRGYARRAY = "data"

    const val CATGRY_URL = BASE_URL2 + "category"
    const val KEY_CTRGYNAME = "title"
    const val KEY_CTRGYID = "id"
    const val JSON_CTRGYARRAY = "data"

    const val SUBCATGRY_URL = BASE_URL2 + "sub-category"
    const val KEY_SUBCTRGYNAME = "title"
    const val KEY_SUBCTRGYID = "id"
    const val JSON_SUBCTRGYARRAY = "data"

    const val CRS_CATGRY_URL = BASE_URL2 + "coursecategory"
    const val KEY_CRS_CTRGYNAME = "title"
    const val KEY_CRS_CTRGYID = "id"
    const val JSON_CRS_CTRGYARRAY = "data"

    const val CRS_SUBCATGRY_URL = BASE_URL2 + "coursesubcategory"
    const val KEY_CRS_SUBCTRGYNAME = "title"
    const val KEY_CRS_SUBCTRGYID = "id"
    const val JSON_CRS_SUBCTRGYARRAY = "data"

    const val LOCATION_URL_BL = "https://nationlearns.com/api/states"
    const val KEY_SID_BL = "id"
    const val KEY_SNAME_BL = "name"
    const val JSON_SARRAY_BL = "statebl"

    const val CLOCATION_URL_BL = "https://nationlearns.com/api/city/"
    const val KEY_CID_BL = "id"
    const val KEY_CNAME_BL = "name"
    const val JSON_CARRAY_BL = "citybl"

    const val PIN_URL_BL = BASE_URL2 + "pincode/"
    const val KEY_PINID_BL = "id"
    const val KEY_PINNAME_BL = "pincode"
    const val JSON_PINARRAY_BL = "pincode_list"

    const val MY_SOCKET_TIMEOUT_MS = 600000
    const val SECRET_KEY = "a67eb62b-2a93-4919-a02f-5d0d76c24201"
    const val COUNTRY_URL_BL = BASE_URL2 + "countries"
    const val KEY_COUNTRY_ID = "id"
    const val KEY_COUNTRY_NAME = "name"
    const val JSON_COUNTRY_ARRAY = "countriesbl"
    const val STATE_URL_BL = BASE_URL2 + "statesnlp/"
    const val KEY_STATE_ID = "id"
    const val KEY_STATE_NAME = "name"
    const val JSON_STATE_ARRAY = "statebl"
    const val CITY_URL_BL = BASE_URL2 + "citynlp/"
    const val KEY_CITY_ID = "id"
    const val KEY_CITY_NAME = "name"
    const val JSON_CITY_ARRAY = "citybl"
}

