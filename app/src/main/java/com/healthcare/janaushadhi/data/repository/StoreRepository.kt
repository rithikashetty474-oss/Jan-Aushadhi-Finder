package com.healthcare.janaushadhi.data.repository

import com.healthcare.janaushadhi.data.models.Store

object StoreRepository {
    private val _stores = mutableListOf<Store>()

    // Map of medicineGenericName -> list of store IDs that have it
    private val stockData = mutableMapOf<String, MutableList<Int>>()

    init {
        _stores.addAll(getAllIndiaStores())
        initStockData()
    }

    fun getAllStores(): List<Store> = _stores.toList()

    fun getStoresByCity(city: String): List<Store> {
        return _stores.filter { it.city.equals(city, ignoreCase = true) }
    }

    fun getStoresWithinRadius(userLat: Double, userLng: Double, radiusKm: Float): List<Store> {
        return _stores.map { store ->
            store.copy(distance = calculateDistance(userLat, userLng, store.latitude, store.longitude))
        }.filter { it.distance <= radiusKm }.sortedBy { it.distance }
    }

    fun updateStoreDistances(userLat: Double, userLng: Double) {
        _stores.forEachIndexed { index, store ->
            _stores[index] = store.copy(
                distance = calculateDistance(userLat, userLng, store.latitude, store.longitude)
            )
        }
    }

    // Returns list of stores that have this medicine in stock
    fun getStoresWithMedicine(genericName: String): List<Store> {
        val key = genericName.lowercase().trim()
        val storeIds = stockData.entries
            .filter { it.key.contains(key) || key.contains(it.key) }
            .flatMap { it.value }
            .distinct()
        return _stores.filter { it.id in storeIds }
    }

    // Returns true/false if a specific store has a medicine
    fun isAvailableAtStore(genericName: String, storeId: Int): Boolean {
        val key = genericName.lowercase().trim()
        return stockData.entries
            .filter { it.key.contains(key) || key.contains(it.key) }
            .any { storeId in it.value }
    }

    fun getAllCities(): List<String> {
        return _stores.map { it.city }.distinct().sorted()
    }

    fun addStore(store: Store) {
        _stores.add(store)
    }

    private fun calculateDistance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Float {
        val r = 6371.0
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = kotlin.math.sin(dLat / 2) * kotlin.math.sin(dLat / 2) +
                kotlin.math.cos(Math.toRadians(lat1)) * kotlin.math.cos(Math.toRadians(lat2)) *
                kotlin.math.sin(dLon / 2) * kotlin.math.sin(dLon / 2)
        val c = 2 * kotlin.math.atan2(kotlin.math.sqrt(a), kotlin.math.sqrt(1 - a))
        return (r * c).toFloat()
    }

    // Simulated stock data: generic name -> store IDs that have it
    private fun initStockData() {
        stockData["paracetamol"] = mutableListOf(1, 2, 3, 5, 7, 11, 15, 20, 21, 30, 41, 51, 61, 71)
        stockData["amoxicillin"] = mutableListOf(2, 4, 6, 12, 16, 22, 25, 42, 52, 62, 72)
        stockData["metformin"] = mutableListOf(1, 3, 8, 13, 17, 23, 26, 43, 53, 63, 73)
        stockData["atorvastatin"] = mutableListOf(2, 5, 9, 14, 18, 24, 27, 44, 54, 64, 74)
        stockData["omeprazole"] = mutableListOf(3, 6, 10, 11, 19, 25, 28, 45, 55, 65, 75)
        stockData["amlodipine"] = mutableListOf(1, 4, 7, 12, 20, 26, 29, 46, 56, 66, 76)
        stockData["cetirizine"] = mutableListOf(2, 5, 8, 13, 21, 27, 30, 47, 57, 67, 77)
        stockData["azithromycin"] = mutableListOf(3, 6, 9, 14, 22, 28, 31, 48, 58, 68, 78)
        stockData["pantoprazole"] = mutableListOf(4, 7, 10, 15, 23, 29, 32, 49, 59, 69, 79)
        stockData["ibuprofen"] = mutableListOf(1, 5, 8, 11, 16, 24, 30, 50, 60, 70, 80)
        stockData["dolo 650"] = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        stockData["aspirin"] = mutableListOf(10, 11, 12, 20, 21, 22, 51, 52, 61, 62, 71, 72)
        stockData["ciprofloxacin"] = mutableListOf(15, 16, 17, 25, 26, 51, 52, 61, 71)
        stockData["losartan"] = mutableListOf(20, 21, 22, 30, 41, 51, 61, 71, 81)
        stockData["montelukast"] = mutableListOf(3, 5, 11, 20, 22, 51, 55, 61, 71)
    }

    private fun getAllIndiaStores() = listOf(

        // ── KARNATAKA ──────────────────────────────────────────────

        // Mangalore
        Store(1,  "Jan Aushadhi Kendra - Hampankatta",   "Hampankatta Circle, Mangalore",      12.8699, 74.8428, "0824-2441234", 0f, "Mangalore",    "Karnataka", true, "09:00 AM", "09:00 PM"),
        Store(2,  "Jan Aushadhi Kendra - Kankanady",     "Kankanady Market Road, Mangalore",   12.8887, 74.8458, "0824-2222345", 0f, "Mangalore",    "Karnataka", true, "09:00 AM", "09:00 PM"),
        Store(3,  "Jan Aushadhi Kendra - Kadri",         "Kadri Temple Road, Mangalore",       12.8976, 74.8634, "0824-2445678", 0f, "Mangalore",    "Karnataka", true, "09:00 AM", "09:00 PM"),
        Store(4,  "Jan Aushadhi Kendra - Bejai",         "Bejai Main Road, Mangalore",         12.8824, 74.8715, "0824-2446789", 0f, "Mangalore",    "Karnataka", true, "09:00 AM", "09:00 PM"),
        Store(5,  "Jan Aushadhi Kendra - Valencia",      "Valencia Circle, Mangalore",         12.8644, 74.8827, "0824-2447890", 0f, "Mangalore",    "Karnataka", true, "09:00 AM", "09:00 PM"),
        Store(6,  "Jan Aushadhi Kendra - Falnir",        "Falnir Road, Mangalore",             12.8711, 74.8502, "0824-2448901", 0f, "Mangalore",    "Karnataka", true, "09:00 AM", "09:00 PM"),
        Store(7,  "Jan Aushadhi Kendra - Balmatta",      "Balmatta Road, Mangalore",           12.8662, 74.8421, "0824-2449012", 0f, "Mangalore",    "Karnataka", true, "09:00 AM", "09:00 PM"),
        Store(8,  "Jan Aushadhi Kendra - Pumpwell",      "Pumpwell Circle, Mangalore",         12.8886, 74.8334, "0824-2440123", 0f, "Mangalore",    "Karnataka", true, "09:00 AM", "09:00 PM"),
        Store(9,  "Jan Aushadhi Kendra - Kottara",       "Kottara Chowki, Mangalore",          12.8944, 74.8891, "0824-2441235", 0f, "Mangalore",    "Karnataka", true, "09:00 AM", "09:00 PM"),
        Store(10, "Jan Aushadhi Kendra - Kodialbail",    "Kodialbail Main Road, Mangalore",    12.8802, 74.8524, "0824-2442346", 0f, "Mangalore",    "Karnataka", true, "09:00 AM", "09:00 PM"),

        // Surathkal
        Store(11, "Jan Aushadhi Kendra - Surathkal",     "NITK Road, Surathkal",               13.0067, 74.7931, "0824-2474123", 0f, "Surathkal",    "Karnataka", true, "09:00 AM", "09:00 PM"),
        Store(12, "Jan Aushadhi Kendra - Mukka",         "NH 66, Mukka, Surathkal",            13.0344, 74.7883, "0824-2475234", 0f, "Surathkal",    "Karnataka", true, "09:00 AM", "09:00 PM"),
        Store(13, "Jan Aushadhi Kendra - Katipalla",     "Katipalla Junction, Surathkal",      13.0156, 74.8012, "0824-2476345", 0f, "Surathkal",    "Karnataka", true, "09:00 AM", "09:00 PM"),
        Store(14, "Jan Aushadhi Kendra - NITK Campus",   "NITK Campus, Surathkal",             13.0110, 74.7909, "0824-2477456", 0f, "Surathkal",    "Karnataka", true, "09:00 AM", "09:00 PM"),

        // Udupi
        Store(15, "Jan Aushadhi Kendra - Udupi Town",   "KM Marg, Udupi",                     13.3409, 74.7421, "0820-2520123", 0f, "Udupi",        "Karnataka", true, "09:00 AM", "09:00 PM"),
        Store(16, "Jan Aushadhi Kendra - Manipal",      "MIT Road, Manipal",                   13.3472, 74.7870, "0820-2571234", 0f, "Udupi",        "Karnataka", true, "09:00 AM", "09:00 PM"),
        Store(17, "Jan Aushadhi Kendra - Kundapura",    "Main Road, Kundapura",                13.6269, 74.6877, "08254-232345", 0f, "Udupi",        "Karnataka", true, "09:00 AM", "09:00 PM"),
        Store(18, "Jan Aushadhi Kendra - Karkala",      "Bus Stand Road, Karkala",             13.2114, 74.9905, "08258-232456", 0f, "Udupi",        "Karnataka", true, "09:00 AM", "09:00 PM"),
        Store(19, "Jan Aushadhi Kendra - Brahmavar",    "NH 66, Brahmavar",                    13.4417, 74.7514, "0820-2522567", 0f, "Udupi",        "Karnataka", true, "09:00 AM", "09:00 PM"),

        // Bangalore
        Store(20, "Jan Aushadhi Kendra - Jayanagar",    "4th Block, Jayanagar",                12.9250, 77.5940, "080-26543210", 0f, "Bangalore",    "Karnataka", true, "09:00 AM", "09:00 PM"),
        Store(21, "Jan Aushadhi Kendra - Koramangala",  "5th Cross, Koramangala",              12.9352, 77.6245, "080-26789012", 0f, "Bangalore",    "Karnataka", true, "09:00 AM", "09:00 PM"),
        Store(22, "Jan Aushadhi Kendra - Indiranagar",  "100 Feet Road, Indiranagar",          12.9784, 77.6408, "080-25612345", 0f, "Bangalore",    "Karnataka", true, "09:00 AM", "09:00 PM"),
        Store(23, "Jan Aushadhi Kendra - Whitefield",   "ITPL Main Road, Whitefield",          12.9698, 77.7499, "080-28456789", 0f, "Bangalore",    "Karnataka", true, "09:00 AM", "09:00 PM"),
        Store(24, "Jan Aushadhi Kendra - Malleshwaram", "8th Cross, Malleshwaram",             13.0031, 77.5707, "080-23341234", 0f, "Bangalore",    "Karnataka", true, "09:00 AM", "09:00 PM"),
        Store(25, "Jan Aushadhi Kendra - BTM Layout",   "2nd Stage, BTM Layout",               12.9165, 77.6101, "080-26754321", 0f, "Bangalore",    "Karnataka", true, "09:00 AM", "09:00 PM"),
        Store(26, "Jan Aushadhi Kendra - Banashankari", "3rd Stage, Banashankari",             12.9250, 77.5486, "080-26987654", 0f, "Bangalore",    "Karnataka", true, "09:00 AM", "09:00 PM"),
        Store(27, "Jan Aushadhi Kendra - HSR Layout",   "Sector 1, HSR Layout",                12.9121, 77.6446, "080-25678901", 0f, "Bangalore",    "Karnataka", true, "09:00 AM", "09:00 PM"),
        Store(28, "Jan Aushadhi Kendra - Electronic City","Phase 1, Electronic City",          12.8456, 77.6603, "080-28901234", 0f, "Bangalore",    "Karnataka", true, "09:00 AM", "09:00 PM"),
        Store(29, "Jan Aushadhi Kendra - Hebbal",       "Outer Ring Road, Hebbal",             13.0358, 77.5970, "080-23456789", 0f, "Bangalore",    "Karnataka", true, "09:00 AM", "09:00 PM"),
        Store(30, "Jan Aushadhi Kendra - Marathahalli", "ORR, Marathahalli",                   12.9591, 77.6974, "080-25123456", 0f, "Bangalore",    "Karnataka", true, "09:00 AM", "09:00 PM"),

        // Other Karnataka
        Store(41, "Jan Aushadhi Kendra - Puttur",       "Main Road, Puttur",                   12.7596, 75.2060, "08251-232567", 0f, "Puttur",       "Karnataka", true, "09:00 AM", "09:00 PM"),
        Store(42, "Jan Aushadhi Kendra - Bantwal",      "BC Road, Bantwal",                    12.8970, 75.0358, "08255-232678", 0f, "Bantwal",      "Karnataka", true, "09:00 AM", "09:00 PM"),
        Store(44, "Jan Aushadhi Kendra - Dharmasthala", "Temple Road, Dharmasthala",           12.9495, 75.3813, "08256-232890", 0f, "Dharmasthala", "Karnataka", true, "09:00 AM", "09:00 PM"),
        Store(45, "Jan Aushadhi Kendra - Moodbidri",    "Main Road, Moodbidri",                13.0683, 74.9942, "08258-232901", 0f, "Moodbidri",    "Karnataka", true, "09:00 AM", "09:00 PM"),
        Store(46, "Jan Aushadhi Kendra - Sullia",       "Town Circle, Sullia",                 12.5592, 75.3881, "08257-233012", 0f, "Sullia",       "Karnataka", true, "09:00 AM", "09:00 PM"),
        Store(47, "Jan Aushadhi Kendra - Belthangady",  "Main Road, Belthangady",              13.0278, 75.3099, "08256-233123", 0f, "Belthangady",  "Karnataka", true, "09:00 AM", "09:00 PM"),
        Store(48, "Jan Aushadhi Kendra - Mulki",        "NH 66, Mulki",                        13.0896, 74.7932, "0824-2478123", 0f, "Mulki",        "Karnataka", true, "09:00 AM", "09:00 PM"),
        Store(49, "Jan Aushadhi Kendra - Ullal",        "Ullal Main Road",                     12.8068, 74.8581, "0824-2479234", 0f, "Ullal",        "Karnataka", true, "09:00 AM", "09:00 PM"),
        Store(50, "Jan Aushadhi Kendra - Padubidri",    "Main Road, Padubidri",                13.1330, 74.7587, "08258-233234", 0f, "Padubidri",    "Karnataka", true, "09:00 AM", "09:00 PM"),
        Store(40, "Jan Aushadhi Kendra - Mysuru",       "Sayyaji Rao Road, Mysuru",            12.2958, 76.6394, "0821-2522345", 0f, "Mysuru",       "Karnataka", true, "09:00 AM", "09:00 PM"),
        Store(39, "Jan Aushadhi Kendra - Hubballi",     "Lamington Road, Hubballi",            15.3647, 75.1240, "0836-2362345", 0f, "Hubballi",     "Karnataka", true, "09:00 AM", "09:00 PM"),
        Store(38, "Jan Aushadhi Kendra - Belagavi",     "College Road, Belagavi",              15.8497, 74.4977, "0831-2423456", 0f, "Belagavi",     "Karnataka", true, "09:00 AM", "09:00 PM"),
        Store(37, "Jan Aushadhi Kendra - Davangere",    "PJ Extension, Davangere",             14.4644, 75.9218, "08192-232456", 0f, "Davangere",    "Karnataka", true, "09:00 AM", "09:00 PM"),
        Store(36, "Jan Aushadhi Kendra - Shivamogga",   "BH Road, Shivamogga",                14.1995, 75.5681, "08182-222567", 0f, "Shivamogga",   "Karnataka", true, "09:00 AM", "09:00 PM"),
        Store(35, "Jan Aushadhi Kendra - Tumakuru",     "SS Puram, Tumakuru",                  13.3409, 77.1010, "0816-2252678", 0f, "Tumakuru",     "Karnataka", true, "09:00 AM", "09:00 PM"),
        Store(34, "Jan Aushadhi Kendra - Vijayapura",   "Station Road, Vijayapura",            16.8302, 75.7100, "08352-252789", 0f, "Vijayapura",   "Karnataka", true, "09:00 AM", "09:00 PM"),
        Store(33, "Jan Aushadhi Kendra - Ballari",      "Gandhi Nagar, Ballari",               15.1394, 76.9214, "08392-252890", 0f, "Ballari",      "Karnataka", true, "09:00 AM", "09:00 PM"),
        Store(32, "Jan Aushadhi Kendra - Raichur",      "Main Road, Raichur",                  16.2120, 77.3439, "08532-222901", 0f, "Raichur",      "Karnataka", true, "09:00 AM", "09:00 PM"),
        Store(31, "Jan Aushadhi Kendra - Hassan",       "Bus Stand Road, Hassan",              13.0033, 76.0998, "08172-262012", 0f, "Hassan",       "Karnataka", true, "09:00 AM", "09:00 PM"),

        // ── MAHARASHTRA ────────────────────────────────────────────
        Store(51, "Jan Aushadhi Kendra - Mumbai Central","Dr. Babasaheb Ambedkar Road, Mumbai",18.9696, 72.8193, "022-23456789", 0f, "Mumbai",       "Maharashtra", true, "09:00 AM", "09:00 PM"),
        Store(52, "Jan Aushadhi Kendra - Andheri",      "S V Road, Andheri West, Mumbai",     19.1136, 72.8697, "022-26789012", 0f, "Mumbai",       "Maharashtra", true, "09:00 AM", "09:00 PM"),
        Store(53, "Jan Aushadhi Kendra - Thane",        "Gokhale Road, Thane",                19.2183, 72.9781, "022-25234567", 0f, "Thane",        "Maharashtra", true, "09:00 AM", "09:00 PM"),
        Store(54, "Jan Aushadhi Kendra - Pune Deccan",  "Deccan Gymkhana, Pune",              18.5167, 73.8473, "020-25678901", 0f, "Pune",         "Maharashtra", true, "09:00 AM", "09:00 PM"),
        Store(55, "Jan Aushadhi Kendra - Pune Hadapsar","Magarpatta Road, Hadapsar",          18.5089, 73.9259, "020-26789012", 0f, "Pune",         "Maharashtra", true, "09:00 AM", "09:00 PM"),
        Store(56, "Jan Aushadhi Kendra - Nagpur",       "Sitabuldi, Nagpur",                  21.1458, 79.0882, "0712-2567890", 0f, "Nagpur",       "Maharashtra", true, "09:00 AM", "09:00 PM"),
        Store(57, "Jan Aushadhi Kendra - Nashik",       "College Road, Nashik",               19.9975, 73.7898, "0253-2312345", 0f, "Nashik",       "Maharashtra", true, "09:00 AM", "09:00 PM"),
        Store(58, "Jan Aushadhi Kendra - Aurangabad",   "Jalna Road, Aurangabad",             19.8762, 75.3433, "0240-2334567", 0f, "Aurangabad",   "Maharashtra", true, "09:00 AM", "09:00 PM"),

        // ── TAMIL NADU ─────────────────────────────────────────────
        Store(61, "Jan Aushadhi Kendra - Chennai Anna Nagar","Anna Nagar, Chennai",            13.0850, 80.2101, "044-26234567", 0f, "Chennai",      "Tamil Nadu", true, "09:00 AM", "09:00 PM"),
        Store(62, "Jan Aushadhi Kendra - Chennai T Nagar","T Nagar, Chennai",                 13.0418, 80.2341, "044-24567890", 0f, "Chennai",      "Tamil Nadu", true, "09:00 AM", "09:00 PM"),
        Store(63, "Jan Aushadhi Kendra - Coimbatore",   "RS Puram, Coimbatore",               11.0168, 76.9558, "0422-2456789", 0f, "Coimbatore",   "Tamil Nadu", true, "09:00 AM", "09:00 PM"),
        Store(64, "Jan Aushadhi Kendra - Madurai",      "Anna Nagar, Madurai",                9.9252,  78.1198, "0452-2345678", 0f, "Madurai",      "Tamil Nadu", true, "09:00 AM", "09:00 PM"),
        Store(65, "Jan Aushadhi Kendra - Salem",        "Fairlands, Salem",                   11.6643, 78.1460, "0427-2345679", 0f, "Salem",        "Tamil Nadu", true, "09:00 AM", "09:00 PM"),
        Store(66, "Jan Aushadhi Kendra - Tiruchirappalli","Thillai Nagar, Trichy",            10.7905, 78.7047, "0431-2345670", 0f, "Trichy",       "Tamil Nadu", true, "09:00 AM", "09:00 PM"),

        // ── KERALA ─────────────────────────────────────────────────
        Store(43, "Jan Aushadhi Kendra - Kasaragod",    "NH 66, Kasaragod",                   12.4996, 74.9869, "04994-232789", 0f, "Kasaragod",    "Kerala",      true, "09:00 AM", "09:00 PM"),
        Store(71, "Jan Aushadhi Kendra - Thiruvananthapuram","MG Road, Thiruvananthapuram",   8.5241,  76.9366, "0471-2345678", 0f, "Thiruvananthapuram","Kerala", true, "09:00 AM", "09:00 PM"),
        Store(72, "Jan Aushadhi Kendra - Kochi",        "MG Road, Ernakulam",                 9.9312,  76.2673, "0484-2356789", 0f, "Kochi",        "Kerala",      true, "09:00 AM", "09:00 PM"),
        Store(73, "Jan Aushadhi Kendra - Kozhikode",    "SM Street, Kozhikode",               11.2588, 75.7804, "0495-2367890", 0f, "Kozhikode",    "Kerala",      true, "09:00 AM", "09:00 PM"),
        Store(74, "Jan Aushadhi Kendra - Thrissur",     "Round South, Thrissur",              10.5276, 76.2144, "0487-2378901", 0f, "Thrissur",     "Kerala",      true, "09:00 AM", "09:00 PM"),
        Store(75, "Jan Aushadhi Kendra - Kannur",       "Town Hall Road, Kannur",             11.8745, 75.3704, "0497-2389012", 0f, "Kannur",       "Kerala",      true, "09:00 AM", "09:00 PM"),

        // ── DELHI / NCR ────────────────────────────────────────────
        Store(81, "Jan Aushadhi Kendra - Connaught Place","Connaught Place, New Delhi",       28.6315, 77.2167, "011-23456789", 0f, "New Delhi",    "Delhi",       true, "09:00 AM", "09:00 PM"),
        Store(82, "Jan Aushadhi Kendra - Rohini",       "Sector 3, Rohini, Delhi",            28.7217, 77.1174, "011-27892345", 0f, "Delhi",        "Delhi",       true, "09:00 AM", "09:00 PM"),
        Store(83, "Jan Aushadhi Kendra - Dwarka",       "Sector 10, Dwarka, Delhi",           28.5921, 77.0460, "011-25093456", 0f, "Delhi",        "Delhi",       true, "09:00 AM", "09:00 PM"),
        Store(84, "Jan Aushadhi Kendra - Noida",        "Sector 18, Noida",                   28.5706, 77.3219, "0120-4234567", 0f, "Noida",        "Uttar Pradesh",true,"09:00 AM", "09:00 PM"),
        Store(85, "Jan Aushadhi Kendra - Gurgaon",      "DLF Phase 2, Gurgaon",               28.4595, 77.0266, "0124-4345678", 0f, "Gurgaon",      "Haryana",     true, "09:00 AM", "09:00 PM"),
        Store(86, "Jan Aushadhi Kendra - Faridabad",    "NIT Area, Faridabad",                28.4089, 77.3178, "0129-4234567", 0f, "Faridabad",    "Haryana",     true, "09:00 AM", "09:00 PM"),

        // ── TELANGANA / AP ─────────────────────────────────────────
        Store(91, "Jan Aushadhi Kendra - Hyderabad Banjara","Banjara Hills, Hyderabad",       17.4126, 78.4483, "040-23456789", 0f, "Hyderabad",    "Telangana",   true, "09:00 AM", "09:00 PM"),
        Store(92, "Jan Aushadhi Kendra - Hyderabad SR Nagar","SR Nagar, Hyderabad",           17.4399, 78.4482, "040-23567890", 0f, "Hyderabad",    "Telangana",   true, "09:00 AM", "09:00 PM"),
        Store(93, "Jan Aushadhi Kendra - Warangal",     "Hanamkonda, Warangal",               17.9784, 79.5941, "0870-2456789", 0f, "Warangal",     "Telangana",   true, "09:00 AM", "09:00 PM"),
        Store(94, "Jan Aushadhi Kendra - Vijayawada",   "Governorpet, Vijayawada",            16.5062, 80.6480, "0866-2456789", 0f, "Vijayawada",   "Andhra Pradesh",true,"09:00 AM","09:00 PM"),
        Store(95, "Jan Aushadhi Kendra - Visakhapatnam","Dwaraka Nagar, Visakhapatnam",       17.7231, 83.3012, "0891-2456789", 0f, "Visakhapatnam","Andhra Pradesh",true,"09:00 AM","09:00 PM"),

        // ── GUJARAT ────────────────────────────────────────────────
        Store(101,"Jan Aushadhi Kendra - Ahmedabad CG","CG Road, Ahmedabad",                  23.0225, 72.5714, "079-26456789", 0f, "Ahmedabad",    "Gujarat",     true, "09:00 AM", "09:00 PM"),
        Store(102,"Jan Aushadhi Kendra - Surat",       "Ring Road, Surat",                    21.1702, 72.8311, "0261-2456789", 0f, "Surat",        "Gujarat",     true, "09:00 AM", "09:00 PM"),
        Store(103,"Jan Aushadhi Kendra - Vadodara",    "Alkapuri, Vadodara",                  22.3072, 73.1812, "0265-2456789", 0f, "Vadodara",     "Gujarat",     true, "09:00 AM", "09:00 PM"),
        Store(104,"Jan Aushadhi Kendra - Rajkot",      "Kalawad Road, Rajkot",                22.3039, 70.8022, "0281-2456789", 0f, "Rajkot",       "Gujarat",     true, "09:00 AM", "09:00 PM"),

        // ── RAJASTHAN ──────────────────────────────────────────────
        Store(111,"Jan Aushadhi Kendra - Jaipur",      "MI Road, Jaipur",                     26.9124, 75.7873, "0141-2456789", 0f, "Jaipur",       "Rajasthan",   true, "09:00 AM", "09:00 PM"),
        Store(112,"Jan Aushadhi Kendra - Jodhpur",     "Sardarpura, Jodhpur",                 26.2389, 73.0243, "0291-2456789", 0f, "Jodhpur",      "Rajasthan",   true, "09:00 AM", "09:00 PM"),
        Store(113,"Jan Aushadhi Kendra - Udaipur",     "Chetak Circle, Udaipur",              24.5854, 73.7125, "0294-2456789", 0f, "Udaipur",      "Rajasthan",   true, "09:00 AM", "09:00 PM"),
        Store(114,"Jan Aushadhi Kendra - Kota",        "Nayapura, Kota",                      25.2138, 75.8648, "0744-2456789", 0f, "Kota",         "Rajasthan",   true, "09:00 AM", "09:00 PM"),

        // ── WEST BENGAL ────────────────────────────────────────────
        Store(121,"Jan Aushadhi Kendra - Kolkata Park St","Park Street, Kolkata",             22.5535, 88.3519, "033-22456789", 0f, "Kolkata",      "West Bengal", true, "09:00 AM", "09:00 PM"),
        Store(122,"Jan Aushadhi Kendra - Kolkata Salt Lake","Sector V, Salt Lake",            22.5726, 88.4162, "033-23456789", 0f, "Kolkata",      "West Bengal", true, "09:00 AM", "09:00 PM"),
        Store(123,"Jan Aushadhi Kendra - Howrah",      "GT Road, Howrah",                     22.5958, 88.2636, "033-26456789", 0f, "Howrah",       "West Bengal", true, "09:00 AM", "09:00 PM"),

        // ── MADHYA PRADESH ─────────────────────────────────────────
        Store(131,"Jan Aushadhi Kendra - Bhopal",      "MP Nagar, Bhopal",                    23.2599, 77.4126, "0755-2456789", 0f, "Bhopal",       "Madhya Pradesh",true,"09:00 AM","09:00 PM"),
        Store(132,"Jan Aushadhi Kendra - Indore",      "MG Road, Indore",                     22.7196, 75.8577, "0731-2456789", 0f, "Indore",       "Madhya Pradesh",true,"09:00 AM","09:00 PM"),
        Store(133,"Jan Aushadhi Kendra - Gwalior",     "Lashkar, Gwalior",                    26.2183, 78.1828, "0751-2456789", 0f, "Gwalior",      "Madhya Pradesh",true,"09:00 AM","09:00 PM"),

        // ── UTTAR PRADESH ──────────────────────────────────────────
        Store(141,"Jan Aushadhi Kendra - Lucknow",     "Hazratganj, Lucknow",                 26.8467, 80.9462, "0522-2456789", 0f, "Lucknow",      "Uttar Pradesh",true,"09:00 AM","09:00 PM"),
        Store(142,"Jan Aushadhi Kendra - Kanpur",      "Mall Road, Kanpur",                   26.4499, 80.3319, "0512-2456789", 0f, "Kanpur",       "Uttar Pradesh",true,"09:00 AM","09:00 PM"),
        Store(143,"Jan Aushadhi Kendra - Varanasi",    "Lanka, Varanasi",                     25.3176, 82.9739, "0542-2456789", 0f, "Varanasi",     "Uttar Pradesh",true,"09:00 AM","09:00 PM"),
        Store(144,"Jan Aushadhi Kendra - Agra",        "Sanjay Place, Agra",                  27.1767, 78.0081, "0562-2456789", 0f, "Agra",         "Uttar Pradesh",true,"09:00 AM","09:00 PM"),
        Store(145,"Jan Aushadhi Kendra - Prayagraj",   "Civil Lines, Prayagraj",              25.4358, 81.8463, "0532-2456789", 0f, "Prayagraj",    "Uttar Pradesh",true,"09:00 AM","09:00 PM"),

        // ── PUNJAB / HARYANA ───────────────────────────────────────
        Store(151,"Jan Aushadhi Kendra - Chandigarh",  "Sector 17, Chandigarh",               30.7333, 76.7794, "0172-2456789", 0f, "Chandigarh",   "Chandigarh",  true, "09:00 AM", "09:00 PM"),
        Store(152,"Jan Aushadhi Kendra - Ludhiana",    "Ferozepur Road, Ludhiana",            30.9010, 75.8573, "0161-2456789", 0f, "Ludhiana",     "Punjab",      true, "09:00 AM", "09:00 PM"),
        Store(153,"Jan Aushadhi Kendra - Amritsar",    "Lawrence Road, Amritsar",             31.6340, 74.8723, "0183-2456789", 0f, "Amritsar",     "Punjab",      true, "09:00 AM", "09:00 PM"),

        // ── ODISHA ─────────────────────────────────────────────────
        Store(161,"Jan Aushadhi Kendra - Bhubaneswar", "Janpath, Bhubaneswar",                20.2961, 85.8245, "0674-2456789", 0f, "Bhubaneswar",  "Odisha",      true, "09:00 AM", "09:00 PM"),
        Store(162,"Jan Aushadhi Kendra - Cuttack",     "College Square, Cuttack",             20.4625, 85.8830, "0671-2456789", 0f, "Cuttack",      "Odisha",      true, "09:00 AM", "09:00 PM"),

        // ── BIHAR / JHARKHAND ──────────────────────────────────────
        Store(171,"Jan Aushadhi Kendra - Patna",       "Fraser Road, Patna",                  25.5941, 85.1376, "0612-2456789", 0f, "Patna",        "Bihar",       true, "09:00 AM", "09:00 PM"),
        Store(172,"Jan Aushadhi Kendra - Ranchi",      "Main Road, Ranchi",                   23.3441, 85.3096, "0651-2456789", 0f, "Ranchi",       "Jharkhand",   true, "09:00 AM", "09:00 PM"),

        // ── ASSAM / NORTHEAST ──────────────────────────────────────
        Store(181,"Jan Aushadhi Kendra - Guwahati",    "GS Road, Guwahati",                   26.1445, 91.7362, "0361-2456789", 0f, "Guwahati",     "Assam",       true, "09:00 AM", "09:00 PM"),

        // ── GOA ────────────────────────────────────────────────────
        Store(191,"Jan Aushadhi Kendra - Panaji",      "18 June Road, Panaji",                15.4909, 73.8278, "0832-2456789", 0f, "Panaji",       "Goa",         true, "09:00 AM", "09:00 PM"),
        Store(192,"Jan Aushadhi Kendra - Margao",      "Abade Faria Road, Margao",            15.2832, 73.9862, "0832-2756789", 0f, "Margao",       "Goa",         true, "09:00 AM", "09:00 PM")
    )
}