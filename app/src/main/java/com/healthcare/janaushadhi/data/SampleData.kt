package com.healthcare.janaushadhi.data

import com.healthcare.janaushadhi.data.models.Medicine
import com.healthcare.janaushadhi.data.models.Store

object SampleData {

    fun getMedicines() = listOf(
        // Pain Relief & Fever
        Medicine(1, "Crocin 650mg", "Paracetamol", 45.0, 5.0, "Pain Relief"),
        Medicine(2, "Dolo 650mg", "Paracetamol", 38.0, 5.0, "Pain Relief"),
        Medicine(3, "Calpol 500mg", "Paracetamol", 42.0, 4.0, "Pain Relief"),
        Medicine(4, "Combiflam", "Ibuprofen + Paracetamol", 85.0, 12.0, "Pain Relief"),
        Medicine(5, "Brufen 400mg", "Ibuprofen", 65.0, 8.0, "Pain Relief"),
        Medicine(6, "Disprin", "Aspirin", 35.0, 6.0, "Pain Relief"),
        Medicine(7, "Voveran 50mg", "Diclofenac", 95.0, 15.0, "Pain Relief"),
        Medicine(8, "Voveran SR 75mg", "Diclofenac SR", 125.0, 22.0, "Pain Relief"),
        Medicine(9, "Flexon", "Ibuprofen + Paracetamol", 78.0, 11.0, "Pain Relief"),
        Medicine(10, "Nise 100mg", "Nimesulide", 68.0, 10.0, "Pain Relief"),

        // Antibiotics
        Medicine(11, "Azithral 500mg", "Azithromycin", 245.0, 35.0, "Antibiotic"),
        Medicine(12, "Azee 500mg", "Azithromycin", 238.0, 35.0, "Antibiotic"),
        Medicine(13, "Augmentin 625mg", "Amoxicillin + Clavulanic Acid", 385.0, 55.0, "Antibiotic"),
        Medicine(14, "Monocef 1gm", "Ceftriaxone", 520.0, 78.0, "Antibiotic"),
        Medicine(15, "Cifran 500mg", "Ciprofloxacin", 165.0, 28.0, "Antibiotic"),
        Medicine(16, "Zifi 200mg", "Cefixime", 198.0, 32.0, "Antibiotic"),
        Medicine(17, "Amoxyclav 625mg", "Amoxicillin + Clavulanate", 368.0, 52.0, "Antibiotic"),
        Medicine(18, "Levoflox 500mg", "Levofloxacin", 225.0, 38.0, "Antibiotic"),
        Medicine(19, "Doxycycline 100mg", "Doxycycline", 155.0, 25.0, "Antibiotic"),
        Medicine(20, "Erythromycin 250mg", "Erythromycin", 142.0, 22.0, "Antibiotic"),

        // Allergy & Cold
        Medicine(21, "Allegra 120mg", "Fexofenadine", 285.0, 42.0, "Allergy"),
        Medicine(22, "Allegra 180mg", "Fexofenadine", 335.0, 55.0, "Allergy"),
        Medicine(23, "Cetrizine 10mg", "Cetirizine", 78.0, 12.0, "Allergy"),
        Medicine(24, "Montair LC", "Montelukast + Levocetirizine", 195.0, 32.0, "Allergy"),
        Medicine(25, "Montair 10mg", "Montelukast", 168.0, 28.0, "Allergy"),
        Medicine(26, "Avil 25mg", "Pheniramine", 58.0, 9.0, "Allergy"),
        Medicine(27, "Benedryl", "Diphenhydramine", 65.0, 11.0, "Allergy"),
        Medicine(28, "Sinarest", "Paracetamol + Phenylephrine", 48.0, 8.0, "Cold"),
        Medicine(29, "D-Cold Total", "Paracetamol + Phenylephrine", 52.0, 9.0, "Cold"),
        Medicine(30, "Cheston Cold", "Paracetamol + Cetirizine", 72.0, 13.0, "Cold"),

        // Acidity & Digestion
        Medicine(31, "Pan 40mg", "Pantoprazole", 125.0, 18.0, "Acidity"),
        Medicine(32, "Pantocid 40mg", "Pantoprazole", 132.0, 18.0, "Acidity"),
        Medicine(33, "Omez 20mg", "Omeprazole", 98.0, 15.0, "Acidity"),
        Medicine(34, "Ranitidine 150mg", "Ranitidine", 68.0, 11.0, "Acidity"),
        Medicine(35, "Rantac 150mg", "Ranitidine", 72.0, 11.0, "Acidity"),
        Medicine(36, "Rabeprazole 20mg", "Rabeprazole", 145.0, 22.0, "Acidity"),
        Medicine(37, "Aciloc 150mg", "Ranitidine", 75.0, 12.0, "Acidity"),
        Medicine(38, "Esomeprazole 40mg", "Esomeprazole", 185.0, 28.0, "Acidity"),
        Medicine(39, "Gelusil MPS", "Antacid", 45.0, 8.0, "Acidity"),
        Medicine(40, "Digene", "Antacid", 38.0, 7.0, "Acidity"),

        // Diabetes
        Medicine(41, "Glycomet 500mg", "Metformin", 185.0, 28.0, "Diabetes"),
        Medicine(42, "Glycomet GP1", "Metformin + Glimepiride", 265.0, 42.0, "Diabetes"),
        Medicine(43, "Januvia 100mg", "Sitagliptin", 685.0, 125.0, "Diabetes"),
        Medicine(44, "Galvus Met 50/500", "Vildagliptin + Metformin", 495.0, 85.0, "Diabetes"),
        Medicine(45, "Glimisave M1", "Glimepiride + Metformin", 248.0, 38.0, "Diabetes"),
        Medicine(46, "Amaryl 2mg", "Glimepiride", 195.0, 32.0, "Diabetes"),
        Medicine(47, "Gluconorm G1", "Glimepiride + Metformin", 252.0, 39.0, "Diabetes"),
        Medicine(48, "Pioglitazone 15mg", "Pioglitazone", 165.0, 28.0, "Diabetes"),
        Medicine(49, "Gliclazide 80mg", "Gliclazide", 142.0, 24.0, "Diabetes"),
        Medicine(50, "Voglibose 0.3mg", "Voglibose", 158.0, 26.0, "Diabetes"),

        // Blood Pressure
        Medicine(51, "Telma 40mg", "Telmisartan", 245.0, 38.0, "Blood Pressure"),
        Medicine(52, "Telma AM", "Telmisartan + Amlodipine", 298.0, 48.0, "Blood Pressure"),
        Medicine(53, "Amlodipine 5mg", "Amlodipine", 165.0, 25.0, "Blood Pressure"),
        Medicine(54, "Amlong 5mg", "Amlodipine", 172.0, 26.0, "Blood Pressure"),
        Medicine(55, "Atenolol 50mg", "Atenolol", 125.0, 20.0, "Blood Pressure"),
        Medicine(56, "Concor 5mg", "Bisoprolol", 195.0, 32.0, "Blood Pressure"),
        Medicine(57, "Metoprolol 50mg", "Metoprolol", 148.0, 24.0, "Blood Pressure"),
        Medicine(58, "Losartan 50mg", "Losartan", 178.0, 28.0, "Blood Pressure"),
        Medicine(59, "Olmesartan 20mg", "Olmesartan", 225.0, 36.0, "Blood Pressure"),
        Medicine(60, "Ramipril 5mg", "Ramipril", 158.0, 26.0, "Blood Pressure"),

        // Cholesterol
        Medicine(61, "Atorvastatin 10mg", "Atorvastatin", 285.0, 45.0, "Cholesterol"),
        Medicine(62, "Atorvastatin 20mg", "Atorvastatin", 368.0, 58.0, "Cholesterol"),
        Medicine(63, "Rosuvastatin 10mg", "Rosuvastatin", 325.0, 52.0, "Cholesterol"),
        Medicine(64, "Rosuvastatin 20mg", "Rosuvastatin", 425.0, 68.0, "Cholesterol"),
        Medicine(65, "Lipitor 10mg", "Atorvastatin", 295.0, 45.0, "Cholesterol"),
        Medicine(66, "Crestor 10mg", "Rosuvastatin", 335.0, 52.0, "Cholesterol"),
        Medicine(67, "Simvastatin 20mg", "Simvastatin", 198.0, 32.0, "Cholesterol"),
        Medicine(68, "Fenofibrate 145mg", "Fenofibrate", 245.0, 38.0, "Cholesterol"),
        Medicine(69, "Gemfibrozil 600mg", "Gemfibrozil", 215.0, 35.0, "Cholesterol"),
        Medicine(70, "Ezetimibe 10mg", "Ezetimibe", 385.0, 62.0, "Cholesterol"),

        // Vitamins & Supplements
        Medicine(71, "Becosules", "Vitamin B Complex", 68.0, 12.0, "Vitamins"),
        Medicine(72, "Neurobion Forte", "Vitamin B Complex", 75.0, 14.0, "Vitamins"),
        Medicine(73, "Supradyn", "Multivitamin", 185.0, 32.0, "Vitamins"),
        Medicine(74, "Zincovit", "Multivitamin + Minerals", 128.0, 22.0, "Vitamins"),
        Medicine(75, "Shelcal 500", "Calcium + Vitamin D3", 145.0, 25.0, "Vitamins"),
        Medicine(76, "Calcimax", "Calcium + Vitamin D3", 138.0, 24.0, "Vitamins"),
        Medicine(77, "Vitamin D3 60K", "Cholecalciferol", 95.0, 18.0, "Vitamins"),
        Medicine(78, "Iron Folic Acid", "Iron + Folic Acid", 52.0, 10.0, "Vitamins"),
        Medicine(79, "Omega 3 Capsules", "Omega 3 Fatty Acids", 285.0, 48.0, "Vitamins"),
        Medicine(80, "Evion 400", "Vitamin E", 98.0, 18.0, "Vitamins"),

        // Respiratory
        Medicine(81, "Asthalin Inhaler", "Salbutamol", 165.0, 28.0, "Respiratory"),
        Medicine(82, "Budecort Inhaler", "Budesonide", 485.0, 85.0, "Respiratory"),
        Medicine(83, "Seroflo Inhaler", "Salmeterol + Fluticasone", 685.0, 125.0, "Respiratory"),
        Medicine(84, "Deriphyllin", "Theophylline", 125.0, 22.0, "Respiratory"),
        Medicine(85, "Ambrodil S", "Ambroxol + Salbutamol", 98.0, 18.0, "Respiratory"),
        Medicine(86, "Levolin Inhaler", "Levosalbutamol", 178.0, 32.0, "Respiratory"),
        Medicine(87, "Tiova Inhaler", "Tiotropium", 525.0, 95.0, "Respiratory"),
        Medicine(88, "Formoterol Inhaler", "Formoterol", 395.0, 68.0, "Respiratory"),
        Medicine(89, "Montelukast 10mg", "Montelukast", 168.0, 28.0, "Respiratory"),
        Medicine(90, "Bambuterol 10mg", "Bambuterol", 195.0, 35.0, "Respiratory"),

        // Anti-anxiety & Sleep
        Medicine(91, "Alprazolam 0.5mg", "Alprazolam", 58.0, 12.0, "Anxiety"),
        Medicine(92, "Clonazepam 0.5mg", "Clonazepam", 68.0, 14.0, "Anxiety"),
        Medicine(93, "Lonazep 0.5mg", "Clonazepam", 72.0, 14.0, "Anxiety"),
        Medicine(94, "Zolpidem 10mg", "Zolpidem", 125.0, 24.0, "Sleep"),
        Medicine(95, "Etizolam 0.5mg", "Etizolam", 78.0, 15.0, "Anxiety"),
        Medicine(96, "Escitalopram 10mg", "Escitalopram", 148.0, 28.0, "Anxiety"),
        Medicine(97, "Fluoxetine 20mg", "Fluoxetine", 132.0, 25.0, "Anxiety"),
        Medicine(98, "Sertraline 50mg", "Sertraline", 165.0, 32.0, "Anxiety"),
        Medicine(99, "Trazodone 50mg", "Trazodone", 142.0, 28.0, "Sleep"),
        Medicine(100, "Mirtazapine 15mg", "Mirtazapine", 185.0, 35.0, "Sleep"),

        // Thyroid
        Medicine(101, "Eltroxin 50mcg", "Levothyroxine", 95.0, 18.0, "Thyroid"),
        Medicine(102, "Eltroxin 100mcg", "Levothyroxine", 125.0, 22.0, "Thyroid"),
        Medicine(103, "Thyronorm 50mcg", "Levothyroxine", 98.0, 18.0, "Thyroid"),
        Medicine(104, "Thyronorm 100mcg", "Levothyroxine", 128.0, 22.0, "Thyroid"),
        Medicine(105, "Carbimazole 5mg", "Carbimazole", 165.0, 28.0, "Thyroid"),
        Medicine(106, "PTU 50mg", "Propylthiouracil", 142.0, 25.0, "Thyroid"),
        Medicine(107, "Neomercazole 5mg", "Carbimazole", 172.0, 28.0, "Thyroid"),
        Medicine(108, "Liothyronine 25mcg", "Liothyronine", 248.0, 42.0, "Thyroid"),

        // Skin Care
        Medicine(109, "Betnovate Cream", "Betamethasone", 85.0, 16.0, "Skin"),
        Medicine(110, "Candid Cream", "Clotrimazole", 68.0, 12.0, "Skin"),
        Medicine(111, "Lobate GM", "Clobetasol + Miconazole", 125.0, 22.0, "Skin"),
        Medicine(112, "Quadriderm", "Betamethasone + Gentamicin", 148.0, 26.0, "Skin"),
        Medicine(113, "Terbinafine Cream", "Terbinafine", 95.0, 18.0, "Skin"),
        Medicine(114, "Mometasone Cream", "Mometasone", 112.0, 20.0, "Skin"),
        Medicine(115, "Fluconazole 150mg", "Fluconazole", 58.0, 12.0, "Skin"),
        Medicine(116, "Isotretinoin 20mg", "Isotretinoin", 485.0, 85.0, "Skin"),
        Medicine(117, "Adapalene Gel", "Adapalene", 285.0, 48.0, "Skin"),
        Medicine(118, "Clindamycin Gel", "Clindamycin", 198.0, 35.0, "Skin"),

        // Eye Care
        Medicine(119, "Refresh Tears", "Carboxymethylcellulose", 165.0, 28.0, "Eye"),
        Medicine(120, "Moxifloxacin Eye Drops", "Moxifloxacin", 125.0, 22.0, "Eye"),
        Medicine(121, "Timolol Eye Drops", "Timolol", 95.0, 18.0, "Eye"),
        Medicine(122, "Latanoprost Eye Drops", "Latanoprost", 385.0, 68.0, "Eye"),
        Medicine(123, "Tobramycin Eye Drops", "Tobramycin", 148.0, 26.0, "Eye"),
        Medicine(124, "Prednisolone Eye Drops", "Prednisolone", 112.0, 20.0, "Eye"),
        Medicine(125, "Ketorolac Eye Drops", "Ketorolac", 98.0, 18.0, "Eye"),

        // Continue with more categories...
        Medicine(126, "Paracip 500mg", "Paracetamol", 40.0, 5.0, "Pain Relief"),
        Medicine(127, "Metacin 500mg", "Paracetamol", 42.0, 5.0, "Pain Relief"),
        Medicine(128, "Pyrigesic 650mg", "Paracetamol", 46.0, 6.0, "Pain Relief"),
        Medicine(129, "T-98 Tablet", "Paracetamol", 38.0, 5.0, "Pain Relief"),
        Medicine(130, "Fepanil 650mg", "Paracetamol", 44.0, 6.0, "Pain Relief"),

        // More Antibiotics
        Medicine(131, "Cepodem XP 200mg", "Cefpodoxime", 298.0, 48.0, "Antibiotic"),
        Medicine(132, "Ceftas 200mg", "Cefixime", 205.0, 34.0, "Antibiotic"),
        Medicine(133, "Taxim O 200mg", "Cefixime", 212.0, 35.0, "Antibiotic"),
        Medicine(134, "Norflox TZ", "Norfloxacin + Tinidazole", 168.0, 28.0, "Antibiotic"),
        Medicine(135, "Ofloxacin 200mg", "Ofloxacin", 145.0, 25.0, "Antibiotic"),
        Medicine(136, "Tinidazole 500mg", "Tinidazole", 95.0, 18.0, "Antibiotic"),
        Medicine(137, "Secnidazole 1gm", "Secnidazole", 178.0, 32.0, "Antibiotic"),
        Medicine(138, "Ornidazole 500mg", "Ornidazole", 125.0, 22.0, "Antibiotic"),
        Medicine(139, "Nitrofurantoin 100mg", "Nitrofurantoin", 148.0, 26.0, "Antibiotic"),
        Medicine(140, "Cephalexin 500mg", "Cephalexin", 185.0, 32.0, "Antibiotic"),

        // More Pain Relief
        Medicine(141, "Aceclofenac 100mg", "Aceclofenac", 72.0, 14.0, "Pain Relief"),
        Medicine(142, "Naproxen 500mg", "Naproxen", 85.0, 16.0, "Pain Relief"),
        Medicine(143, "Indomethacin 25mg", "Indomethacin", 68.0, 13.0, "Pain Relief"),
        Medicine(144, "Piroxicam 20mg", "Piroxicam", 78.0, 15.0, "Pain Relief"),
        Medicine(145, "Etoricoxib 90mg", "Etoricoxib", 125.0, 24.0, "Pain Relief"),
        Medicine(146, "Tramadol 50mg", "Tramadol", 95.0, 18.0, "Pain Relief"),
        Medicine(147, "Ketorolac 10mg", "Ketorolac", 88.0, 17.0, "Pain Relief"),
        Medicine(148, "Mefenamic Acid 500mg", "Mefenamic Acid", 72.0, 14.0, "Pain Relief"),
        Medicine(149, "Paracetamol + Tramadol", "Paracetamol + Tramadol", 135.0, 26.0, "Pain Relief"),
        Medicine(150, "Aceclofenac + Paracetamol", "Aceclofenac + Paracetamol", 92.0, 18.0, "Pain Relief"),

        // Heart & Cardiovascular
        Medicine(151, "Clopidogrel 75mg", "Clopidogrel", 245.0, 42.0, "Heart"),
        Medicine(152, "Aspirin 75mg", "Aspirin", 48.0, 10.0, "Heart"),
        Medicine(153, "Ecosprin 75mg", "Aspirin", 52.0, 10.0, "Heart"),
        Medicine(154, "Ticagrelor 90mg", "Ticagrelor", 685.0, 125.0, "Heart"),
        Medicine(155, "Carvedilol 6.25mg", "Carvedilol", 148.0, 26.0, "Heart"),
        Medicine(156, "Nebivolol 5mg", "Nebivolol", 195.0, 35.0, "Heart"),
        Medicine(157, "Digoxin 0.25mg", "Digoxin", 78.0, 15.0, "Heart"),
        Medicine(158, "Ivabradine 5mg", "Ivabradine", 425.0, 75.0, "Heart"),
        Medicine(159, "Ranolazine 500mg", "Ranolazine", 385.0, 68.0, "Heart"),
        Medicine(160, "Trimetazidine 35mg", "Trimetazidine", 295.0, 52.0, "Heart"),

        // Antihistamines
        Medicine(161, "Loratadine 10mg", "Loratadine", 68.0, 13.0, "Allergy"),
        Medicine(162, "Desloratadine 5mg", "Desloratadine", 125.0, 24.0, "Allergy"),
        Medicine(163, "Levocetirizine 5mg", "Levocetirizine", 78.0, 15.0, "Allergy"),
        Medicine(164, "Hydroxyzine 25mg", "Hydroxyzine", 95.0, 18.0, "Allergy"),
        Medicine(165, "Chlorpheniramine 4mg", "Chlorpheniramine", 38.0, 8.0, "Allergy"),
        Medicine(166, "Fexofenadine 120mg", "Fexofenadine", 285.0, 42.0, "Allergy"),
        Medicine(167, "Bilastine 20mg", "Bilastine", 245.0, 38.0, "Allergy"),
        Medicine(168, "Ebastine 10mg", "Ebastine", 165.0, 28.0, "Allergy"),

        // Antiemetics
        Medicine(169, "Ondansetron 4mg", "Ondansetron", 95.0, 18.0, "Nausea"),
        Medicine(170, "Domperidone 10mg", "Domperidone", 68.0, 13.0, "Nausea"),
        Medicine(171, "Metoclopramide 10mg", "Metoclopramide", 52.0, 11.0, "Nausea"),
        Medicine(172, "Prochlorperazine 5mg", "Prochlorperazine", 78.0, 15.0, "Nausea"),
        Medicine(173, "Granisetron 1mg", "Granisetron", 245.0, 42.0, "Nausea"),

        // Antacids
        Medicine(174, "Sucralfate 1gm", "Sucralfate", 125.0, 22.0, "Acidity"),
        Medicine(175, "Famotidine 20mg", "Famotidine", 95.0, 18.0, "Acidity"),
        Medicine(176, "Nizatidine 150mg", "Nizatidine", 108.0, 20.0, "Acidity"),
        Medicine(177, "Lansoprazole 30mg", "Lansoprazole", 168.0, 28.0, "Acidity"),
        Medicine(178, "Dexlansoprazole 60mg", "Dexlansoprazole", 285.0, 48.0, "Acidity"),
        Medicine(179, "Itopride 50mg", "Itopride", 125.0, 22.0, "Acidity"),
        Medicine(180, "Mosapride 5mg", "Mosapride", 142.0, 25.0, "Acidity"),

        // Laxatives
        Medicine(181, "Dulcolax 5mg", "Bisacodyl", 58.0, 12.0, "Laxative"),
        Medicine(182, "Lactulose Syrup", "Lactulose", 125.0, 24.0, "Laxative"),
        Medicine(183, "Isabgol", "Psyllium Husk", 95.0, 18.0, "Laxative"),
        Medicine(184, "Cremaffin", "Liquid Paraffin", 108.0, 20.0, "Laxative"),
        Medicine(185, "Sodium Picosulfate", "Sodium Picosulfate", 78.0, 15.0, "Laxative"),

        // Antidiarrheals
        Medicine(186, "Loperamide 2mg", "Loperamide", 48.0, 10.0, "Diarrhea"),
        Medicine(187, "Racecadotril 100mg", "Racecadotril", 125.0, 24.0, "Diarrhea"),
        Medicine(188, "Oflomac OZ", "Ofloxacin + Ornidazole", 148.0, 26.0, "Diarrhea"),
        Medicine(189, "Norflox TZ", "Norfloxacin + Tinidazole", 168.0, 28.0, "Diarrhea"),
        Medicine(190, "ORS Sachets", "Oral Rehydration Salts", 15.0, 4.0, "Diarrhea"),

        // Urinary Tract
        Medicine(191, "Cranberry Extract", "Cranberry", 285.0, 48.0, "Urinary"),
        Medicine(192, "Potassium Citrate", "Potassium Citrate", 125.0, 22.0, "Urinary"),
        Medicine(193, "Tamsulosin 0.4mg", "Tamsulosin", 195.0, 35.0, "Urinary"),
        Medicine(194, "Alfuzosin 10mg", "Alfuzosin", 245.0, 42.0, "Urinary"),
        Medicine(195, "Silodosin 8mg", "Silodosin", 385.0, 68.0, "Urinary"),
        Medicine(196, "Solifenacin 5mg", "Solifenacin", 325.0, 58.0, "Urinary"),
        Medicine(197, "Tolterodine 2mg", "Tolterodine", 298.0, 52.0, "Urinary"),
        Medicine(198, "Dutasteride 0.5mg", "Dutasteride", 425.0, 75.0, "Urinary"),
        Medicine(199, "Finasteride 5mg", "Finasteride", 245.0, 42.0, "Urinary"),
        Medicine(200, "Tadalafil 5mg", "Tadalafil", 385.0, 68.0, "Urinary"),

        // Women's Health
        Medicine(201, "Meprate 10mg", "Medroxyprogesterone", 125.0, 24.0, "Women's Health"),
        Medicine(202, "Duphaston 10mg", "Dydrogesterone", 385.0, 68.0, "Women's Health"),
        Medicine(203, "Deviry 10mg", "Medroxyprogesterone", 132.0, 24.0, "Women's Health"),
        Medicine(204, "Clomiphene 50mg", "Clomiphene", 195.0, 35.0, "Women's Health"),
        Medicine(205, "Letrozole 2.5mg", "Letrozole", 285.0, 48.0, "Women's Health"),
        Medicine(206, "Mifepristone 200mg", "Mifepristone", 385.0, 68.0, "Women's Health"),
        Medicine(207, "Estradiol Valerate", "Estradiol", 245.0, 42.0, "Women's Health"),
        Medicine(208, "Progesterone 200mg", "Progesterone", 325.0, 58.0, "Women's Health"),
        Medicine(209, "Tranexamic Acid 500mg", "Tranexamic Acid", 148.0, 26.0, "Women's Health"),
        Medicine(210, "Mefenamic Acid 250mg", "Mefenamic Acid", 65.0, 13.0, "Women's Health"),

        // Multivitamins & More
        Medicine(211, "A to Z Multivitamin", "Multivitamin", 198.0, 35.0, "Vitamins"),
        Medicine(212, "Revital H", "Multivitamin", 245.0, 42.0, "Vitamins"),
        Medicine(213, "HealthVit", "Multivitamin", 168.0, 32.0, "Vitamins"),
        Medicine(214, "Centrum", "Multivitamin", 385.0, 68.0, "Vitamins"),
        Medicine(215, "Vitamin B12 1000mcg", "Methylcobalamin", 125.0, 24.0, "Vitamins"),
        Medicine(216, "Folic Acid 5mg", "Folic Acid", 48.0, 10.0, "Vitamins"),
        Medicine(217, "Biotin 10mg", "Biotin", 285.0, 48.0, "Vitamins"),
        Medicine(218, "Vitamin C 500mg", "Ascorbic Acid", 95.0, 18.0, "Vitamins"),
        Medicine(219, "Calcium Carbonate 500mg", "Calcium", 68.0, 14.0, "Vitamins"),
        Medicine(220, "Magnesium Oxide 400mg", "Magnesium", 125.0, 24.0, "Vitamins"),

        // Antiparasitics
        Medicine(221, "Albendazole 400mg", "Albendazole", 48.0, 10.0, "Antiparasitic"),
        Medicine(222, "Mebendazole 100mg", "Mebendazole", 52.0, 11.0, "Antiparasitic"),
        Medicine(223, "Ivermectin 12mg", "Ivermectin", 95.0, 18.0, "Antiparasitic"),
        Medicine(224, "Praziquantel 600mg", "Praziquantel", 125.0, 24.0, "Antiparasitic"),
        Medicine(225, "Metronidazole 400mg", "Metronidazole", 58.0, 12.0, "Antiparasitic"),

        // Antifungals
        Medicine(226, "Itraconazole 100mg", "Itraconazole", 285.0, 48.0, "Antifungal"),
        Medicine(227, "Terbinafine 250mg", "Terbinafine", 245.0, 42.0, "Antifungal"),
        Medicine(228, "Griseofulvin 500mg", "Griseofulvin", 195.0, 35.0, "Antifungal"),
        Medicine(229, "Voriconazole 200mg", "Voriconazole", 685.0, 125.0, "Antifungal"),
        Medicine(230, "Ketoconazole 200mg", "Ketoconazole", 168.0, 28.0, "Antifungal"),

        // Continue adding more medicines to reach 500+
        Medicine(231, "Pregabalin 75mg", "Pregabalin", 245.0, 42.0, "Nerve Pain"),
        Medicine(232, "Gabapentin 300mg", "Gabapentin", 195.0, 35.0, "Nerve Pain"),
        Medicine(233, "Methylcobalamin 1500mcg", "Methylcobalamin", 148.0, 26.0, "Nerve Pain"),
        Medicine(234, "Alpha Lipoic Acid 200mg", "Alpha Lipoic Acid", 285.0, 48.0, "Nerve Pain"),
        Medicine(235, "Carbamazepine 200mg", "Carbamazepine", 125.0, 22.0, "Nerve Pain"),
        Medicine(236, "Oxcarbazepine 300mg", "Oxcarbazepine", 245.0, 42.0, "Nerve Pain"),
        Medicine(237, "Phenytoin 100mg", "Phenytoin", 85.0, 16.0, "Epilepsy"),
        Medicine(238, "Valproate 500mg", "Valproate", 168.0, 28.0, "Epilepsy"),
        Medicine(239, "Levetiracetam 500mg", "Levetiracetam", 285.0, 48.0, "Epilepsy"),
        Medicine(240, "Topiramate 50mg", "Topiramate", 245.0, 42.0, "Epilepsy"),

        // Migraine
        Medicine(241, "Sumatriptan 50mg", "Sumatriptan", 385.0, 68.0, "Migraine"),
        Medicine(242, "Rizatriptan 10mg", "Rizatriptan", 425.0, 75.0, "Migraine"),
        Medicine(243, "Naratriptan 2.5mg", "Naratriptan", 485.0, 85.0, "Migraine"),
        Medicine(244, "Propranolol 40mg", "Propranolol", 95.0, 18.0, "Migraine"),
        Medicine(245, "Flunarizine 10mg", "Flunarizine", 125.0, 24.0, "Migraine"),

        // Gout
        Medicine(246, "Allopurinol 100mg", "Allopurinol", 68.0, 14.0, "Gout"),
        Medicine(247, "Febuxostat 40mg", "Febuxostat", 285.0, 48.0, "Gout"),
        Medicine(248, "Colchicine 0.6mg", "Colchicine", 148.0, 26.0, "Gout"),
        Medicine(249, "Probenecid 500mg", "Probenecid", 195.0, 35.0, "Gout"),

        // Osteoporosis
        Medicine(250, "Alendronate 70mg", "Alendronate", 245.0, 42.0, "Osteoporosis"),
        Medicine(251, "Risedronate 35mg", "Risedronate", 385.0, 68.0, "Osteoporosis"),
        Medicine(252, "Ibandronate 150mg", "Ibandronate", 485.0, 85.0, "Osteoporosis"),
        Medicine(253, "Denosumab Injection", "Denosumab", 2850.0, 485.0, "Osteoporosis"),
        Medicine(254, "Teriparatide Injection", "Teriparatide", 3850.0, 685.0, "Osteoporosis"),

        // Anti-HIV/Antiretrovirals
        Medicine(255, "Tenofovir 300mg", "Tenofovir", 685.0, 125.0, "HIV"),
        Medicine(256, "Lamivudine 150mg", "Lamivudine", 245.0, 42.0, "HIV"),
        Medicine(257, "Efavirenz 600mg", "Efavirenz", 425.0, 75.0, "HIV"),
        Medicine(258, "Atazanavir 300mg", "Atazanavir", 1285.0, 225.0, "HIV"),
        Medicine(259, "Ritonavir 100mg", "Ritonavir", 685.0, 125.0, "HIV"),

        // Hepatitis
        Medicine(260, "Sofosbuvir 400mg", "Sofosbuvir", 2850.0, 485.0, "Hepatitis"),
        Medicine(261, "Daclatasvir 60mg", "Daclatasvir", 1850.0, 325.0, "Hepatitis"),
        Medicine(262, "Ribavirin 200mg", "Ribavirin", 485.0, 85.0, "Hepatitis"),
        Medicine(263, "Entecavir 0.5mg", "Entecavir", 685.0, 125.0, "Hepatitis"),
        Medicine(264, "Tenofovir Alafenamide 25mg", "Tenofovir AF", 1285.0, 225.0, "Hepatitis"),

        // Cancer (Oral Chemotherapy)
        Medicine(265, "Capecitabine 500mg", "Capecitabine", 1850.0, 325.0, "Cancer"),
        Medicine(266, "Imatinib 400mg", "Imatinib", 2850.0, 485.0, "Cancer"),
        Medicine(267, "Gefitinib 250mg", "Gefitinib", 3850.0, 685.0, "Cancer"),
        Medicine(268, "Erlotinib 150mg", "Erlotinib", 4850.0, 850.0, "Cancer"),
        Medicine(269, "Tamoxifen 20mg", "Tamoxifen", 125.0, 24.0, "Cancer"),
        Medicine(270, "Anastrozole 1mg", "Anastrozole", 385.0, 68.0, "Cancer"),
        Medicine(271, "Letrozole 2.5mg", "Letrozole", 285.0, 48.0, "Cancer"),
        Medicine(272, "Exemestane 25mg", "Exemestane", 485.0, 85.0, "Cancer"),

        // Immunosuppressants
        Medicine(273, "Azathioprine 50mg", "Azathioprine", 245.0, 42.0, "Immunosuppressant"),
        Medicine(274, "Mycophenolate 500mg", "Mycophenolate", 685.0, 125.0, "Immunosuppressant"),
        Medicine(275, "Tacrolimus 1mg", "Tacrolimus", 1285.0, 225.0, "Immunosuppressant"),
        Medicine(276, "Cyclosporine 100mg", "Cyclosporine", 1850.0, 325.0, "Immunosuppressant"),
        Medicine(277, "Sirolimus 1mg", "Sirolimus", 2850.0, 485.0, "Immunosuppressant"),

        // Rheumatoid Arthritis
        Medicine(278, "Methotrexate 10mg", "Methotrexate", 148.0, 26.0, "Arthritis"),
        Medicine(279, "Hydroxychloroquine 200mg", "Hydroxychloroquine", 95.0, 18.0, "Arthritis"),
        Medicine(280, "Sulfasalazine 500mg", "Sulfasalazine", 125.0, 24.0, "Arthritis"),
        Medicine(281, "Leflunomide 20mg", "Leflunomide", 685.0, 125.0, "Arthritis"),
        Medicine(282, "Tofacitinib 5mg", "Tofacitinib", 2850.0, 485.0, "Arthritis"),

        // More common medicines
        Medicine(283, "Paracetamol IV", "Paracetamol Injection", 125.0, 24.0, "Pain Relief"),
        Medicine(284, "Diclofenac Injection", "Diclofenac Injection", 95.0, 18.0, "Pain Relief"),
        Medicine(285, "Tramadol Injection", "Tramadol Injection", 148.0, 26.0, "Pain Relief"),
        Medicine(286, "Pethidine Injection", "Pethidine", 285.0, 48.0, "Pain Relief"),
        Medicine(287, "Morphine 10mg", "Morphine", 385.0, 68.0, "Pain Relief"),
        Medicine(288, "Fentanyl Patch", "Fentanyl", 1285.0, 225.0, "Pain Relief"),

        // Emergency medicines
        Medicine(289, "Adrenaline Injection", "Epinephrine", 95.0, 18.0, "Emergency"),
        Medicine(290, "Atropine Injection", "Atropine", 68.0, 14.0, "Emergency"),
        Medicine(291, "Dopamine Injection", "Dopamine", 245.0, 42.0, "Emergency"),
        Medicine(292, "Noradrenaline", "Norepinephrine", 385.0, 68.0, "Emergency"),
        Medicine(293, "Hydrocortisone Injection", "Hydrocortisone", 125.0, 24.0, "Emergency"),
        Medicine(294, "Dexamethasone Injection", "Dexamethasone", 95.0, 18.0, "Emergency"),
        Medicine(295, "Methylprednisolone", "Methylprednisolone", 285.0, 48.0, "Emergency"),
        Medicine(296, "Aminophylline Injection", "Aminophylline", 68.0, 14.0, "Emergency"),
        Medicine(297, "Furosemide Injection", "Furosemide", 58.0, 12.0, "Emergency"),
        Medicine(298, "Mannitol Injection", "Mannitol", 195.0, 35.0, "Emergency"),
        Medicine(299, "Phenytoin Injection", "Phenytoin", 148.0, 26.0, "Emergency"),
        Medicine(300, "Midazolam Injection", "Midazolam", 245.0, 42.0, "Emergency"),

        // Continue with 200 more medicines...
        Medicine(301, "Salbutamol Nebulizer", "Salbutamol", 85.0, 16.0, "Respiratory"),
        Medicine(302, "Ipratropium Nebulizer", "Ipratropium", 125.0, 24.0, "Respiratory"),
        Medicine(303, "Budesonide Nebulizer", "Budesonide", 385.0, 68.0, "Respiratory"),
        Medicine(304, "N-Acetylcysteine 600mg", "NAC", 195.0, 35.0, "Respiratory"),
        Medicine(305, "Ambroxol 30mg", "Ambroxol", 68.0, 13.0, "Respiratory"),
        Medicine(306, "Bromhexine 8mg", "Bromhexine", 52.0, 11.0, "Respiratory"),
        Medicine(307, "Guaifenesin 200mg", "Guaifenesin", 78.0, 15.0, "Respiratory"),
        Medicine(308, "Dextromethorphan 10mg", "Dextromethorphan", 95.0, 18.0, "Cough"),
        Medicine(309, "Codeine Linctus", "Codeine", 125.0, 24.0, "Cough"),
        Medicine(310, "Chlorpheniramine Syrup", "Chlorpheniramine", 48.0, 10.0, "Cough"),

        Medicine(311, "Levothyroxine 25mcg", "Levothyroxine", 85.0, 16.0, "Thyroid"),
        Medicine(312, "Levothyroxine 75mcg", "Levothyroxine", 108.0, 20.0, "Thyroid"),
        Medicine(313, "Levothyroxine 125mcg", "Levothyroxine", 135.0, 24.0, "Thyroid"),
        Medicine(314, "Methimazole 5mg", "Methimazole", 148.0, 26.0, "Thyroid"),
        Medicine(315, "Methimazole 10mg", "Methimazole", 185.0, 32.0, "Thyroid"),

        Medicine(316, "Warfarin 5mg", "Warfarin", 95.0, 18.0, "Anticoagulant"),
        Medicine(317, "Rivaroxaban 20mg", "Rivaroxaban", 1285.0, 225.0, "Anticoagulant"),
        Medicine(318, "Apixaban 5mg", "Apixaban", 1485.0, 258.0, "Anticoagulant"),
        Medicine(319, "Dabigatran 150mg", "Dabigatran", 1685.0, 295.0, "Anticoagulant"),
        Medicine(320, "Enoxaparin Injection", "Enoxaparin", 385.0, 68.0, "Anticoagulant"),

        Medicine(321, "Sildenafil 50mg", "Sildenafil", 285.0, 48.0, "ED"),
        Medicine(322, "Tadalafil 20mg", "Tadalafil", 485.0, 85.0, "ED"),
        Medicine(323, "Vardenafil 20mg", "Vardenafil", 685.0, 125.0, "ED"),
        Medicine(324, "Avanafil 100mg", "Avanafil", 885.0, 158.0, "ED"),

        Medicine(325, "Prednisolone 5mg", "Prednisolone", 58.0, 12.0, "Steroid"),
        Medicine(326, "Prednisolone 10mg", "Prednisolone", 78.0, 15.0, "Steroid"),
        Medicine(327, "Prednisolone 20mg", "Prednisolone", 108.0, 20.0, "Steroid"),
        Medicine(328, "Dexamethasone 0.5mg", "Dexamethasone", 68.0, 14.0, "Steroid"),
        Medicine(329, "Betamethasone 0.5mg", "Betamethasone", 85.0, 16.0, "Steroid"),
        Medicine(330, "Deflazacort 6mg", "Deflazacort", 148.0, 26.0, "Steroid"),

        Medicine(331, "Insulin Glargine", "Insulin Glargine", 685.0, 125.0, "Diabetes"),
        Medicine(332, "Insulin Aspart", "Insulin Aspart", 585.0, 105.0, "Diabetes"),
        Medicine(333, "Insulin Lispro", "Insulin Lispro", 625.0, 115.0, "Diabetes"),
        Medicine(334, "Insulin NPH", "Insulin NPH", 285.0, 48.0, "Diabetes"),
        Medicine(335, "Empagliflozin 10mg", "Empagliflozin", 485.0, 85.0, "Diabetes"),
        Medicine(336, "Dapagliflozin 10mg", "Dapagliflozin", 525.0, 95.0, "Diabetes"),
        Medicine(337, "Canagliflozin 100mg", "Canagliflozin", 585.0, 105.0, "Diabetes"),
        Medicine(338, "Sitagliptin 100mg", "Sitagliptin", 685.0, 125.0, "Diabetes"),
        Medicine(339, "Vildagliptin 50mg", "Vildagliptin", 385.0, 68.0, "Diabetes"),
        Medicine(340, "Saxagliptin 5mg", "Saxagliptin", 685.0, 125.0, "Diabetes"),

        Medicine(341, "Luliconazole Cream", "Luliconazole", 245.0, 42.0, "Skin"),
        Medicine(342, "Sertaconazole Cream", "Sertaconazole", 168.0, 28.0, "Skin"),
        Medicine(343, "Eberconazole Cream", "Eberconazole", 195.0, 35.0, "Skin"),
        Medicine(344, "Mupirocin Ointment", "Mupirocin", 148.0, 26.0, "Skin"),
        Medicine(345, "Fusidic Acid Cream", "Fusidic Acid", 168.0, 28.0, "Skin"),
        Medicine(346, "Tacrolimus Ointment", "Tacrolimus", 685.0, 125.0, "Skin"),
        Medicine(347, "Pimecrolimus Cream", "Pimecrolimus", 585.0, 105.0, "Skin"),
        Medicine(348, "Tretinoin Cream", "Tretinoin", 245.0, 42.0, "Skin"),
        Medicine(349, "Benzoyl Peroxide Gel", "Benzoyl Peroxide", 185.0, 32.0, "Skin"),
        Medicine(350, "Salicylic Acid Gel", "Salicylic Acid", 148.0, 26.0, "Skin"),

        Medicine(351, "Brimonidine Eye Drops", "Brimonidine", 245.0, 42.0, "Eye"),
        Medicine(352, "Dorzolamide Eye Drops", "Dorzolamide", 285.0, 48.0, "Eye"),
        Medicine(353, "Brinzolamide Eye Drops", "Brinzolamide", 385.0, 68.0, "Eye"),
        Medicine(354, "Bimatoprost Eye Drops", "Bimatoprost", 485.0, 85.0, "Eye"),
        Medicine(355, "Travoprost Eye Drops", "Travoprost", 425.0, 75.0, "Eye"),
        Medicine(356, "Cyclopentolate Eye Drops", "Cyclopentolate", 95.0, 18.0, "Eye"),
        Medicine(357, "Tropicamide Eye Drops", "Tropicamide", 85.0, 16.0, "Eye"),
        Medicine(358, "Nepafenac Eye Drops", "Nepafenac", 285.0, 48.0, "Eye"),
        Medicine(359, "Flurbiprofen Eye Drops", "Flurbiprofen", 245.0, 42.0, "Eye"),
        Medicine(360, "Olopatadine Eye Drops", "Olopatadine", 325.0, 58.0, "Eye"),

        Medicine(361, "Citicoline 500mg", "Citicoline", 285.0, 48.0, "Brain"),
        Medicine(362, "Piracetam 800mg", "Piracetam", 168.0, 28.0, "Brain"),
        Medicine(363, "Donepezil 10mg", "Donepezil", 485.0, 85.0, "Brain"),
        Medicine(364, "Memantine 10mg", "Memantine", 585.0, 105.0, "Brain"),
        Medicine(365, "Rivastigmine 6mg", "Rivastigmine", 685.0, 125.0, "Brain"),

        Medicine(366, "L-Arginine 500mg", "L-Arginine", 245.0, 42.0, "Supplements"),
        Medicine(367, "Coenzyme Q10 100mg", "CoQ10", 385.0, 68.0, "Supplements"),
        Medicine(368, "Glucosamine 750mg", "Glucosamine", 485.0, 85.0, "Supplements"),
        Medicine(369, "Chondroitin 400mg", "Chondroitin", 585.0, 105.0, "Supplements"),
        Medicine(370, "MSM 1000mg", "Methylsulfonylmethane", 385.0, 68.0, "Supplements"),
        Medicine(371, "Collagen Peptides", "Collagen", 885.0, 158.0, "Supplements"),
        Medicine(372, "Probiotics", "Lactobacillus", 485.0, 85.0, "Supplements"),
        Medicine(373, "Digestive Enzymes", "Multi-Enzyme", 385.0, 68.0, "Supplements"),
        Medicine(374, "Melatonin 3mg", "Melatonin", 285.0, 48.0, "Supplements"),
        Medicine(375, "5-HTP 100mg", "5-Hydroxytryptophan", 685.0, 125.0, "Supplements"),

        Medicine(376, "Lycopene 10mg", "Lycopene", 285.0, 48.0, "Antioxidants"),
        Medicine(377, "Lutein 20mg", "Lutein", 385.0, 68.0, "Antioxidants"),
        Medicine(378, "Astaxanthin 4mg", "Astaxanthin", 885.0, 158.0, "Antioxidants"),
        Medicine(379, "Resveratrol 250mg", "Resveratrol", 1085.0, 195.0, "Antioxidants"),
        Medicine(380, "Curcumin 500mg", "Curcumin", 485.0, 85.0, "Antioxidants"),

        Medicine(381, "Testosterone Gel", "Testosterone", 1685.0, 295.0, "Hormones"),
        Medicine(382, "Growth Hormone", "Somatropin", 3850.0, 685.0, "Hormones"),
        Medicine(383, "Octreotide", "Octreotide", 2850.0, 485.0, "Hormones"),
        Medicine(384, "Bromocriptine 2.5mg", "Bromocriptine", 385.0, 68.0, "Hormones"),
        Medicine(385, "Cabergoline 0.5mg", "Cabergoline", 685.0, 125.0, "Hormones"),

        Medicine(386, "Heparin Injection", "Heparin", 195.0, 35.0, "Anticoagulant"),
        Medicine(387, "Fondaparinux", "Fondaparinux", 1285.0, 225.0, "Anticoagulant"),
        Medicine(388, "Clopidogrel + Aspirin", "Clopidogrel + Aspirin", 285.0, 48.0, "Antiplatelet"),
        Medicine(389, "Prasugrel 10mg", "Prasugrel", 885.0, 158.0, "Antiplatelet"),
        Medicine(390, "Cilostazol 100mg", "Cilostazol", 485.0, 85.0, "Antiplatelet"),

        Medicine(391, "Latanoprost + Timolol", "Latanoprost + Timolol", 485.0, 85.0, "Eye"),
        Medicine(392, "Dorzolamide + Timolol", "Dorzolamide + Timolol", 385.0, 68.0, "Eye"),
        Medicine(393, "Brinzolamide + Brimonidine", "Brinzolamide + Brimonidine", 585.0, 105.0, "Eye"),

        Medicine(394, "Halobetasol Cream", "Halobetasol", 245.0, 42.0, "Skin"),
        Medicine(395, "Clobetasol + Neomycin", "Clobetasol + Neomycin", 168.0, 28.0, "Skin"),
        Medicine(396, "Fluocinonide Cream", "Fluocinonide", 195.0, 35.0, "Skin"),
        Medicine(397, "Triamcinolone Cream", "Triamcinolone", 148.0, 26.0, "Skin"),
        Medicine(398, "Hydrocortisone Cream", "Hydrocortisone", 95.0, 18.0, "Skin"),
        Medicine(399, "Calamine Lotion", "Calamine", 48.0, 10.0, "Skin"),
        Medicine(400, "Aloe Vera Gel", "Aloe Vera", 125.0, 24.0, "Skin"),

        Medicine(401, "Chlorhexidine Mouthwash", "Chlorhexidine", 95.0, 18.0, "Dental"),
        Medicine(402, "Benzydamine Oral Rinse", "Benzydamine", 125.0, 24.0, "Dental"),
        Medicine(403, "Metronidazole Gel Dental", "Metronidazole", 85.0, 16.0, "Dental"),
        Medicine(404, "Triamcinolone Oral Paste", "Triamcinolone", 125.0, 24.0, "Dental"),

        Medicine(405, "Minoxidil 5% Solution", "Minoxidil", 485.0, 85.0, "Hair"),
        Medicine(406, "Finasteride 1mg", "Finasteride", 385.0, 68.0, "Hair"),
        Medicine(407, "Dutasteride 0.5mg", "Dutasteride", 585.0, 105.0, "Hair"),
        Medicine(408, "Biotin Hair Supplement", "Biotin", 385.0, 68.0, "Hair"),

        Medicine(409, "Nitroglycerin Spray", "Nitroglycerin", 285.0, 48.0, "Heart"),
        Medicine(410, "Isosorbide Mononitrate", "Isosorbide", 125.0, 24.0, "Heart"),
        Medicine(411, "Isosorbide Dinitrate", "Isosorbide", 95.0, 18.0, "Heart"),
        Medicine(412, "Diltiazem 30mg", "Diltiazem", 148.0, 26.0, "Heart"),
        Medicine(413, "Verapamil 80mg", "Verapamil", 125.0, 24.0, "Heart"),
        Medicine(414, "Nifedipine 10mg", "Nifedipine", 95.0, 18.0, "Heart"),
        Medicine(415, "Felodipine 5mg", "Felodipine", 168.0, 28.0, "Heart"),

        Medicine(416, "Spironolactone 25mg", "Spironolactone", 95.0, 18.0, "Diuretic"),
        Medicine(417, "Furosemide 40mg", "Furosemide", 48.0, 10.0, "Diuretic"),
        Medicine(418, "Torsemide 10mg", "Torsemide", 85.0, 16.0, "Diuretic"),
        Medicine(419, "Hydrochlorothiazide 12.5mg", "Hydrochlorothiazide", 52.0, 11.0, "Diuretic"),
        Medicine(420, "Indapamide 2.5mg", "Indapamide", 125.0, 24.0, "Diuretic"),

        Medicine(421, "Pentoxifylline 400mg", "Pentoxifylline", 245.0, 42.0, "Circulation"),
        Medicine(422, "Diosmin 600mg", "Diosmin", 285.0, 48.0, "Circulation"),
        Medicine(423, "Troxerutin 500mg", "Troxerutin", 195.0, 35.0, "Circulation"),

        Medicine(424, "Orlistat 120mg", "Orlistat", 685.0, 125.0, "Weight Loss"),
        Medicine(425, "Liraglutide Injection", "Liraglutide", 3850.0, 685.0, "Weight Loss"),
        Medicine(426, "Semaglutide Injection", "Semaglutide", 8850.0, 1585.0, "Weight Loss"),

        Medicine(427, "Acarbose 50mg", "Acarbose", 185.0, 32.0, "Diabetes"),
        Medicine(428, "Miglitol 50mg", "Miglitol", 285.0, 48.0, "Diabetes"),
        Medicine(429, "Repaglinide 1mg", "Repaglinide", 245.0, 42.0, "Diabetes"),
        Medicine(430, "Nateglinide 120mg", "Nateglinide", 325.0, 58.0, "Diabetes"),

        Medicine(431, "Ursodeoxycholic Acid 300mg", "UDCA", 485.0, 85.0, "Liver"),
        Medicine(432, "Silymarin 140mg", "Silymarin", 285.0, 48.0, "Liver"),
        Medicine(433, "L-Ornithine L-Aspartate", "LOLA", 485.0, 85.0, "Liver"),
        Medicine(434, "Ademetionine 400mg", "SAMe", 885.0, 158.0, "Liver"),

        Medicine(435, "Pancreatin Capsules", "Pancreatic Enzymes", 285.0, 48.0, "Digestive"),
        Medicine(436, "Pancrelipase", "Pancrelipase", 685.0, 125.0, "Digestive"),
        Medicine(437, "Simethicone 125mg", "Simethicone", 68.0, 14.0, "Digestive"),
        Medicine(438, "Activated Charcoal", "Activated Charcoal", 95.0, 18.0, "Digestive"),

        Medicine(439, "Acetylcysteine 600mg", "NAC", 185.0, 32.0, "Mucolytic"),
        Medicine(440, "Carbocysteine 750mg", "Carbocysteine", 148.0, 26.0, "Mucolytic"),
        Medicine(441, "Erdosteine 300mg", "Erdosteine", 245.0, 42.0, "Mucolytic"),

        Medicine(442, "Budesonide + Formoterol", "Budesonide + Formoterol", 885.0, 158.0, "Respiratory"),
        Medicine(443, "Fluticasone + Salmeterol", "Fluticasone + Salmeterol", 785.0, 142.0, "Respiratory"),
        Medicine(444, "Beclomethasone Inhaler", "Beclomethasone", 385.0, 68.0, "Respiratory"),
        Medicine(445, "Ciclesonide Inhaler", "Ciclesonide", 685.0, 125.0, "Respiratory"),

        Medicine(446, "Ranitidine Injection", "Ranitidine Injection", 95.0, 18.0, "Acidity"),
        Medicine(447, "Pantoprazole Injection", "Pantoprazole Injection", 148.0, 26.0, "Acidity"),
        Medicine(448, "Omeprazole Injection", "Omeprazole Injection", 125.0, 24.0, "Acidity"),
        Medicine(449, "Esomeprazole Injection", "Esomeprazole Injection", 245.0, 42.0, "Acidity"),

        Medicine(450, "Granisetron Injection", "Granisetron Injection", 385.0, 68.0, "Nausea"),
        Medicine(451, "Palonosetron Injection", "Palonosetron Injection", 885.0, 158.0, "Nausea"),
        Medicine(452, "Aprepitant 125mg", "Aprepitant", 1685.0, 295.0, "Nausea"),

        Medicine(453, "Sodium Bicarbonate", "Sodium Bicarbonate", 38.0, 8.0, "Antacid"),
        Medicine(454, "Magnesium Hydroxide", "Magnesium Hydroxide", 48.0, 10.0, "Antacid"),
        Medicine(455, "Aluminium Hydroxide", "Aluminium Hydroxide", 52.0, 11.0, "Antacid"),

        Medicine(456, "Glyceryl Trinitrate Patch", "GTN", 285.0, 48.0, "Heart"),
        Medicine(457, "Frusemide Injection", "Furosemide Injection", 68.0, 14.0, "Diuretic"),
        Medicine(458, "Spironolactone + Furosemide", "Spironolactone + Furosemide", 125.0, 24.0, "Diuretic"),

        Medicine(459, "Ramipril + Hydrochlorothiazide", "Ramipril + HCTZ", 195.0, 35.0, "Blood Pressure"),
        Medicine(460, "Losartan + Hydrochlorothiazide", "Losartan + HCTZ", 225.0, 38.0, "Blood Pressure"),
        Medicine(461, "Telmisartan + Hydrochlorothiazide", "Telmisartan + HCTZ", 285.0, 48.0, "Blood Pressure"),
        Medicine(462, "Olmesartan + Amlodipine", "Olmesartan + Amlodipine", 385.0, 68.0, "Blood Pressure"),
        Medicine(463, "Valsartan 80mg", "Valsartan", 245.0, 42.0, "Blood Pressure"),
        Medicine(464, "Valsartan + Amlodipine", "Valsartan + Amlodipine", 385.0, 68.0, "Blood Pressure"),
        Medicine(465, "Irbesartan 150mg", "Irbesartan", 285.0, 48.0, "Blood Pressure"),
        Medicine(466, "Candesartan 16mg", "Candesartan", 325.0, 58.0, "Blood Pressure"),

        Medicine(467, "Atorvastatin + Clopidogrel", "Atorvastatin + Clopidogrel", 425.0, 75.0, "Heart"),
        Medicine(468, "Atorvastatin + Aspirin", "Atorvastatin + Aspirin", 325.0, 58.0, "Heart"),
        Medicine(469, "Rosuvastatin + Clopidogrel", "Rosuvastatin + Clopidogrel", 525.0, 95.0, "Heart"),
        Medicine(470, "Rosuvastatin + Aspirin", "Rosuvastatin + Aspirin", 385.0, 68.0, "Heart"),

        Medicine(471, "Pregabalin + Methylcobalamin", "Pregabalin + B12", 325.0, 58.0, "Nerve Pain"),
        Medicine(472, "Gabapentin + Methylcobalamin", "Gabapentin + B12", 245.0, 42.0, "Nerve Pain"),
        Medicine(473, "Duloxetine 30mg", "Duloxetine", 385.0, 68.0, "Nerve Pain"),
        Medicine(474, "Amitriptyline 10mg", "Amitriptyline", 48.0, 10.0, "Nerve Pain"),
        Medicine(475, "Nortriptyline 10mg", "Nortriptyline", 68.0, 14.0, "Nerve Pain"),

        Medicine(476, "Diclofenac + Paracetamol", "Diclofenac + Paracetamol", 95.0, 18.0, "Pain Relief"),
        Medicine(477, "Aceclofenac + Paracetamol", "Aceclofenac + Paracetamol", 105.0, 20.0, "Pain Relief"),
        Medicine(478, "Tramadol + Paracetamol", "Tramadol + Paracetamol", 148.0, 26.0, "Pain Relief"),
        Medicine(479, "Ibuprofen + Paracetamol + Caffeine", "Ibuprofen + Para + Caffeine", 125.0, 24.0, "Pain Relief"),
        Medicine(480, "Diclofenac + Serratiopeptidase", "Diclofenac + Serratiopeptidase", 125.0, 24.0, "Pain Relief"),

        Medicine(481, "Rabeprazole + Domperidone", "Rabeprazole + Domperidone", 185.0, 32.0, "Acidity"),
        Medicine(482, "Pantoprazole + Domperidone", "Pantoprazole + Domperidone", 148.0, 26.0, "Acidity"),
        Medicine(483, "Esomeprazole + Domperidone", "Esomeprazole + Domperidone", 245.0, 42.0, "Acidity"),
        Medicine(484, "Omeprazole + Domperidone", "Omeprazole + Domperidone", 125.0, 24.0, "Acidity"),
        Medicine(485, "Rabeprazole + Levosulpiride", "Rabeprazole + Levosulpiride", 245.0, 42.0, "Acidity"),

        Medicine(486, "Levofloxacin + Ornidazole", "Levofloxacin + Ornidazole", 245.0, 42.0, "Antibiotic"),
        Medicine(487, "Ofloxacin + Ornidazole", "Ofloxacin + Ornidazole", 168.0, 28.0, "Antibiotic"),
        Medicine(488, "Ciprofloxacin + Tinidazole", "Ciprofloxacin + Tinidazole", 185.0, 32.0, "Antibiotic"),
        Medicine(489, "Amoxicillin + Clavulanate + Lactobacillus", "Amoxyclav + LB", 425.0, 75.0, "Antibiotic"),
        Medicine(490, "Azithromycin + Levofloxacin", "Azithromycin + Levofloxacin", 485.0, 85.0, "Antibiotic"),

        Medicine(491, "Montelukast + Levocetirizine + Ambroxol", "Montair LC + Ambroxol", 245.0, 42.0, "Allergy"),
        Medicine(492, "Fexofenadine + Montelukast", "Fexofenadine + Montelukast", 325.0, 58.0, "Allergy"),
        Medicine(493, "Cetirizine + Ambroxol", "Cetirizine + Ambroxol", 125.0, 24.0, "Allergy"),
        Medicine(494, "Levocetirizine + Phenylephrine", "Levocetirizine + Phenylephrine", 148.0, 26.0, "Cold"),
        Medicine(495, "Cetirizine + Phenylephrine + Paracetamol", "Cetirizine + PE + Para", 95.0, 18.0, "Cold"),

        Medicine(496, "Calcium + Vitamin D3 + Methylcobalamin", "Calcium + D3 + B12", 285.0, 48.0, "Vitamins"),
        Medicine(497, "Multivitamin + Multimineral + Antioxidants", "Multivitamin Complete", 485.0, 85.0, "Vitamins"),
        Medicine(498, "Protein Powder Supplement", "Protein Powder", 1285.0, 225.0, "Supplements"),
        Medicine(499, "Mass Gainer Supplement", "Mass Gainer", 1685.0, 295.0, "Supplements"),
        Medicine(500, "Pre-Workout Supplement", "Pre-Workout", 1485.0, 258.0, "Supplements"),

        // Add 5 more to make it 505 total
        Medicine(501, "BCAA Powder", "Branched-Chain Amino Acids", 1085.0, 195.0, "Supplements"),
        Medicine(502, "Creatine Monohydrate", "Creatine", 885.0, 158.0, "Supplements"),
        Medicine(503, "Fish Oil Omega 3", "Fish Oil", 685.0, 125.0, "Supplements"),
        Medicine(504, "Vitamin D3 60000 IU", "Cholecalciferol High Dose", 125.0, 24.0, "Vitamins"),
        Medicine(505, "Electrolyte ORS Powder", "Electrolyte Powder", 25.0, 6.0, "Rehydration")
    )

    fun getStores() = listOf(
        Store(1, "Jan Aushadhi Kendra - Anna Nagar", "2nd Avenue, Anna Nagar, Chennai", 13.0850, 80.2101, "044-26262626", 2.5f),
        Store(2, "Jan Aushadhi Kendra - T Nagar", "Usman Road, T Nagar, Chennai", 13.0418, 80.2341, "044-24341234", 5.2f),
        Store(3, "Jan Aushadhi Kendra - Adyar", "2nd Main Road, Adyar, Chennai", 13.0067, 80.2571, "044-24461234", 8.1f),
        Store(4, "Jan Aushadhi Kendra - Velachery", "100 Feet Road, Velachery, Chennai", 12.9750, 80.2212, "044-22341234", 6.3f),
        Store(5, "Jan Aushadhi Kendra - Porur", "Trunk Road, Porur, Chennai", 13.0358, 80.1563, "044-24781234", 9.7f)
    )
}