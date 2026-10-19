package com.washeed.tanimart.data;

import com.washeed.tanimart.data.entities.Article;
import com.washeed.tanimart.data.entities.Category;
import com.washeed.tanimart.data.entities.Price;
import com.washeed.tanimart.data.entities.Product;
import com.washeed.tanimart.data.entities.ProductSpecification;
import com.washeed.tanimart.data.repository.ArticleRepository;
import com.washeed.tanimart.data.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DatabasePopulator {
    public enum PopulateType { CATEGORY, ARTICLE, PRODUCT }

    public static void populate(PopulateType type, ProductRepository productRepository, ArticleRepository articleRepository) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                switch (type) {
                    case CATEGORY:
                        productRepository.categoryInsert(new Category("Seeds", "🌾", "All,Vegetables,Fruits,Herbs"));
                        productRepository.categoryInsert(new Category("Fertilizers", "🪣", "All,Organic,NPK,Urea,KCl"));
                        productRepository.categoryInsert(new Category("Pesticides", "🧴", "All,Insecticide,Fungicide,Herbicide"));
                        productRepository.categoryInsert(new Category("Agricultural Tools", "⚙️", "All,Hoe,Shovel,Pruner"));
                        productRepository.categoryInsert(new Category("Planting Media", "🌿", "All,Soil,Cocopeat,Compost"));
                        productRepository.categoryInsert(new Category("Livestock Feed", "🐄", "All,Cattle,Poultry,Goat"));
                        productRepository.categoryInsert(new Category("Plant Care", "💧", "All,Growth,Nutrients,Protection"));
                        productRepository.categoryInsert(new Category("Agricultural Equipment", "🌡️", "All,Sensor,Irrigation,Monitoring"));
                        productRepository.categoryInsert(new Category("Pots", "🎋", "All,Plastic,Ceramic,Hanging"));
                        productRepository.categoryInsert(new Category("Pest Control", "🐛", "All,Trap,Repellent,Bait"));
                        productRepository.categoryInsert(new Category("Ornamental Plants", "🌻", "All,Flowering,Indoor,Tropical"));
                        break;
                    case ARTICLE:
                        articleRepository.articleInsert(new Article(
                                "Understanding Brown Planthopper Pests and Their Control Methods",
                                "Brown planthoppers are among the major threats to rice crops. Learn how to identify infestations early and apply effective pest management strategies to minimise crop damage.",
                                "Pests & Diseases", "25 Sep 2026", 2800f, "article1"));
                        articleRepository.articleInsert(new Article(
                                "Superior Seed Nursery Techniques for Better Harvest Yields",
                                "Proper seed selection and nursery management play an important role in improving crop productivity and ensuring healthy plant growth.",
                                "Seeds", "22 Sep 2026", 956f, "article2"));
                        articleRepository.articleInsert(new Article(
                                "Drip Irrigation Systems for Efficient Water Usage in Dry Fields",
                                "Drip irrigation helps conserve water while ensuring crops receive consistent moisture, making it ideal for dry agricultural areas.",
                                "Irrigation", "18 Sep 2026", 3100f, "article3"));
                        articleRepository.articleInsert(new Article(
                                "Ideal Rice Harvesting Time for Premium Grain Quality",
                                "Harvesting rice at the correct maturity stage ensures better grain quality, reduced losses and improved market value.",
                                "Harvest", "14 Sep 2026", 1800f, "article4"));
                        break;
                    case PRODUCT:
                        // Category 1

                        addProduct(productRepository, 1,
                                "Vegetables Kangkung Seeds",
                                "Fast-growing water spinach seeds suitable for warm climates and moist, fertile soil. Sow in prepared beds or containers and keep consistently watered during germination. Harvest young leaves and tender stems for fresh cooking once the plants are established.",
                                "https://images.unsplash.com/photo-1563514227147-6d2ff665a6a0?auto=format&fit=crop&fm=jpg&q=60&w=800",
                                4.8f, 125, 350, 10,
                                new int[]{1,5,10},
                                new int[]{120,500,950},
                                new String[][]{
                                        spec("Plant Type", "Leafy vegetable"),
                                        spec("Common Name", "Kangkung / water spinach"),
                                        spec("Growing Season", "Warm season"),
                                        spec("Light", "Full sun to partial shade"),
                                        spec("Soil", "Moist, fertile soil"),
                                        spec("Harvest", "Tender leaves and stems")
                                });

                        addProduct(productRepository, 1,
                                "Vegetables Lettuce Seeds",
                                "Lettuce seeds for growing tender salad greens in garden beds, pots, or raised planters. Best results come from fertile, well-drained soil and regular watering. Grow in cooler conditions or provide afternoon shade during hot weather to reduce early bolting.",
                                "https://images.unsplash.com/photo-1622205313162-be1d5712a43f?auto=format&fit=crop&fm=jpg&q=60&w=800",
                                4.6f, 80, 210, 0,
                                new int[]{1,5,10},
                                new int[]{110,480,900},
                                new String[][]{
                                        spec("Plant Type", "Leafy vegetable"),
                                        spec("Crop", "Lettuce"),
                                        spec("Growing Season", "Cool season"),
                                        spec("Light", "Full sun or partial shade"),
                                        spec("Soil", "Fertile, well-drained"),
                                        spec("Watering", "Keep soil evenly moist")
                                });

                        addProduct(productRepository, 1,
                                "Fruits Melon Seeds",
                                "Melon seeds for cultivating vining fruit plants in sunny growing spaces. Plant after the soil is warm and provide sufficient room for vines to spread. Consistent moisture during establishment and good drainage help support healthy fruit development.",
                                "https://images.unsplash.com/photo-1571575173700-afb9492e6a50?auto=format&fit=crop&fm=jpg&q=60&w=800",
                                4.9f, 140, 420, 5,
                                new int[]{1,5,10},
                                new int[]{150,700,1300},
                                new String[][]{
                                        spec("Plant Type", "Fruiting vine"),
                                        spec("Crop", "Melon"),
                                        spec("Growing Season", "Warm season"),
                                        spec("Light", "Full sun"),
                                        spec("Soil", "Well-drained and fertile"),
                                        spec("Growing Space", "Room for spreading vines")
                                });

                        addProduct(productRepository, 1,
                                "Herbs Basil Seeds",
                                "Basil seeds for producing fragrant culinary leaves in pots, balconies, and herb gardens. Sow in warm soil with plenty of sunlight and trim the growing tips regularly to encourage branching. Harvest leaves as needed for sauces, salads, and cooking.",
                                "https://images.unsplash.com/photo-1466692476868-aef1dfb1e735?auto=format&fit=crop&fm=jpg&q=60&w=800",
                                4.7f, 55, 120, 0,
                                new int[]{1,5,10},
                                new int[]{100,450,850},
                                new String[][]{
                                        spec("Plant Type", "Culinary herb"),
                                        spec("Crop", "Basil"),
                                        spec("Growing Season", "Warm season"),
                                        spec("Light", "Full sun"),
                                        spec("Soil", "Well-drained potting mix"),
                                        spec("Harvest", "Fresh aromatic leaves")
                                });

                        // Category 2

                        addProduct(productRepository, 2,
                                "NPK Mutiara 16-16-16",
                                "A balanced granular fertilizer formulated with equal proportions of nitrogen, phosphorus, and potassium. Intended for general crop nutrition where soil testing indicates a suitable need for all three major nutrients. Apply according to the product label and local agronomic recommendations.",
                                "https://images.unsplash.com/photo-1696583536539-a37eb2e93080?auto=format&fit=crop&fm=jpg&q=60&w=800",
                                4.9f, 300, 720, 15,
                                new int[]{1,5,10},
                                new int[]{180,800,1500},
                                new String[][]{
                                        spec("Product Type", "Compound fertilizer"),
                                        spec("NPK Grade", "16-16-16"),
                                        spec("Nitrogen (N)", "16%"),
                                        spec("Phosphate (P2O5)", "16%"),
                                        spec("Potash (K2O)", "16%"),
                                        spec("Application", "Follow label and soil test")
                                });

                        addProduct(productRepository, 2,
                                "NPK Growmore Premium",
                                "General-purpose NPK fertilizer for supplementing essential macronutrients during crop growth. Suitable use depends on the formulation, soil conditions, and crop requirements. Confirm the exact nutrient analysis on the package before choosing dosage or application frequency.",
                                "https://images.unsplash.com/photo-1696583536539-a37eb2e93080?auto=format&fit=crop&fm=jpg&q=60&w=800",
                                4.8f, 220, 510, 10,
                                new int[]{1,5,10},
                                new int[]{190,850,1600},
                                new String[][]{
                                        spec("Product Type", "NPK fertilizer"),
                                        spec("Nutrient Group", "Nitrogen, phosphorus, potassium"),
                                        spec("Formulation", "Check package label"),
                                        spec("Application", "Soil or as label directs"),
                                        spec("Crop Suitability", "Depends on crop needs"),
                                        spec("Dosage", "Use label guidance")
                                });

                        addProduct(productRepository, 2,
                                "Urea Petronas Granule",
                                "Granular urea fertilizer used to supply nitrogen for crop growth and leaf development. Apply only in quantities appropriate for the crop and soil conditions. Incorporation or timely irrigation may help reduce nitrogen loss; follow local fertilizer advice and the product instructions.",
                                "https://images.unsplash.com/photo-1696583536539-a37eb2e93080?auto=format&fit=crop&fm=jpg&q=60&w=800",
                                4.7f, 190, 430, 5,
                                new int[]{1,5,10},
                                new int[]{170,780,1450},
                                new String[][]{
                                        spec("Product Type", "Nitrogen fertilizer"),
                                        spec("Primary Nutrient", "Nitrogen"),
                                        spec("Typical Urea N", "46% (verify label)"),
                                        spec("Physical Form", "Granules"),
                                        spec("Use", "Crop nitrogen nutrition"),
                                        spec("Application", "Follow crop-specific guidance")
                                });

                        addProduct(productRepository, 2,
                                "KCl Red Potash",
                                "Potassium chloride fertilizer commonly used to supplement potassium in agricultural soils. Potassium is important for plant water regulation and crop development. Check crop chloride sensitivity and soil nutrient levels before application, and follow the package instructions.",
                                "https://images.unsplash.com/photo-1696583536539-a37eb2e93080?auto=format&fit=crop&fm=jpg&q=60&w=800",
                                4.6f, 110, 300, 0,
                                new int[]{1,5,10},
                                new int[]{160,750,1400},
                                new String[][]{
                                        spec("Product Type", "Potash fertilizer"),
                                        spec("Main Compound", "Potassium chloride (KCl)"),
                                        spec("Primary Nutrient", "Potassium"),
                                        spec("Typical K2O", "About 60% (verify label)"),
                                        spec("Physical Form", "Granular"),
                                        spec("Precaution", "Consider chloride sensitivity")
                                });

                        addProduct(productRepository, 2,
                                "Organic Compost Fertilizer",
                                "Organic compost designed to enrich soil with decomposed organic matter. Mixing mature compost into garden beds may improve soil structure, moisture retention, and biological activity. Its exact nutrient content varies by feedstock and batch, so it should not replace a soil-based nutrition plan.",
                                "https://images.unsplash.com/photo-1696583536539-a37eb2e93080?auto=format&fit=crop&fm=jpg&q=60&w=800",
                                4.8f, 150, 330, 0,
                                new int[]{1,5,10},
                                new int[]{140,650,1200},
                                new String[][]{
                                        spec("Product Type", "Organic soil amendment"),
                                        spec("Main Material", "Decomposed organic matter"),
                                        spec("Use", "Garden beds and planting media"),
                                        spec("Soil Benefit", "Improves soil structure"),
                                        spec("Nutrient Content", "Varies by batch"),
                                        spec("Application", "Mix with existing soil")
                                });

                        // Category 3

                        addProduct(productRepository, 3,
                                "Insecticide Crop Shield",
                                "Crop insect-control product intended for use against label-listed pests. Identify the pest and verify that the product is approved for the crop before use. Follow the manufacturer instructions for dilution, personal protection, application intervals, and the pre-harvest waiting period.",
                                "https://images.unsplash.com/photo-1656407410275-e63e689bcd90?auto=format&fit=crop&fm=jpg&q=60&w=800",
                                4.5f, 90, 210, 0,
                                new int[]{1,5,10},
                                new int[]{200,900,1700},
                                new String[][]{
                                        spec("Product Type", "Insecticide"),
                                        spec("Target", "Label-listed crop insects"),
                                        spec("Active Ingredient", "Check package label"),
                                        spec("Crop Compatibility", "See approved crops"),
                                        spec("Application Rate", "Follow label instructions"),
                                        spec("Safety", "Use required PPE")
                                });

                        addProduct(productRepository, 3,
                                "Fungicide Green Protect",
                                "Fungicide product for managing fungal plant diseases listed on its approved label. Effective management starts with correct disease identification and good crop hygiene. Follow the indicated mixing ratio, application schedule, resistance-management guidance, and safety precautions.",
                                "https://images.unsplash.com/photo-1656407410275-e63e689bcd90?auto=format&fit=crop&fm=jpg&q=60&w=800",
                                4.7f, 120, 250, 10,
                                new int[]{1,5,10},
                                new int[]{210,950,1800},
                                new String[][]{
                                        spec("Product Type", "Fungicide"),
                                        spec("Target", "Label-listed fungal diseases"),
                                        spec("Active Ingredient", "Check package label"),
                                        spec("Application", "As directed on label"),
                                        spec("Resistance Management", "Rotate modes of action"),
                                        spec("Safety", "Observe label precautions")
                                });

                        addProduct(productRepository, 3,
                                "Herbicide Weed Destroyer",
                                "Weed-control product intended for the species and application areas specified by its label. Determine whether treatment is selective or nonselective before spraying near crops. Observe weather, drift-control instructions, protective equipment requirements, and all use restrictions.",
                                "https://images.unsplash.com/photo-1656407410275-e63e689bcd90?auto=format&fit=crop&fm=jpg&q=60&w=800",
                                4.4f, 70, 180, 0,
                                new int[]{1,5,10},
                                new int[]{190,850,1600},
                                new String[][]{
                                        spec("Product Type", "Herbicide"),
                                        spec("Target", "Label-listed weeds"),
                                        spec("Selectivity", "Verify before application"),
                                        spec("Active Ingredient", "Check package label"),
                                        spec("Spray Precaution", "Avoid off-target drift"),
                                        spec("Safety", "Follow all label restrictions")
                                });

                        // Category 4

                        addProduct(productRepository, 4,
                                "Hoe Carbon Steel",
                                "Hand-operated hoe intended for loosening topsoil, breaking small clumps, and removing young weeds. The steel blade is suited to routine garden-bed maintenance, while its manually controlled action makes it useful for working around established plants. Clean and dry the tool after use.",
                                "https://images.unsplash.com/photo-1513467655676-561b7d489a88?auto=format&fit=crop&fm=jpg&q=60&w=800",
                                4.8f, 150, 310, 0,
                                new int[]{1},
                                new int[]{550},
                                new String[][]{
                                        spec("Tool Type", "Garden hoe"),
                                        spec("Blade Material", "Carbon steel"),
                                        spec("Operation", "Manual"),
                                        spec("Primary Use", "Weeding and cultivating"),
                                        spec("Suitable Area", "Garden beds"),
                                        spec("Maintenance", "Clean and dry after use")
                                });

                        addProduct(productRepository, 4,
                                "Shovel Heavy Duty",
                                "General-purpose shovel for digging planting holes, moving loose soil, and handling compost in home or farm gardens. Built for repeated manual gardening tasks, with performance depending on ground conditions. Keep the blade clean and avoid using it as a lever against immovable objects.",
                                "https://images.unsplash.com/photo-1513467655676-561b7d489a88?auto=format&fit=crop&fm=jpg&q=60&w=800",
                                4.7f, 130, 280, 0,
                                new int[]{1},
                                new int[]{620},
                                new String[][]{
                                        spec("Tool Type", "Shovel"),
                                        spec("Operation", "Manual"),
                                        spec("Primary Use", "Digging and soil transfer"),
                                        spec("Suitable Material", "Soil and loose compost"),
                                        spec("Use Area", "Garden and farm"),
                                        spec("Care", "Clean blade after use")
                                });

                        addProduct(productRepository, 4,
                                "Pruner Garden Cutter",
                                "Hand pruner for trimming small branches, dead stems, and unwanted growth around garden plants. Clean cutting blades can help achieve neat pruning cuts when used within the tool capacity. Disinfect the blades between diseased plants and store safely when not in use.",
                                "https://images.unsplash.com/photo-1513467655676-561b7d489a88?auto=format&fit=crop&fm=jpg&q=60&w=800",
                                4.9f, 80, 200, 5,
                                new int[]{1},
                                new int[]{450},
                                new String[][]{
                                        spec("Tool Type", "Hand pruner"),
                                        spec("Operation", "Manual"),
                                        spec("Primary Use", "Trimming stems"),
                                        spec("Suitable Plants", "Shrubs and garden plants"),
                                        spec("Blade Care", "Clean and disinfect"),
                                        spec("Safety", "Keep away from children")
                                });

                        // Category 5

                        addProduct(productRepository, 5,
                                "Soil Premium Garden Mix",
                                "Ready-to-use garden planting mix intended for containers, raised beds, and general planting preparation. Provides a practical growing medium for roots while helping maintain an appropriate balance of drainage and water retention. Adjust watering and feeding based on the crops being grown.",
                                "https://images.unsplash.com/photo-1492496913980-501348b61469?auto=format&fit=crop&fm=jpg&q=60&w=800",
                                4.8f, 100, 210, 0,
                                new int[]{1,5,10},
                                new int[]{100,450,850},
                                new String[][]{
                                        spec("Product Type", "Planting medium"),
                                        spec("Material", "Garden soil blend"),
                                        spec("Application", "Pots and garden beds"),
                                        spec("Drainage", "Depends on mix and container"),
                                        spec("Plant Type", "General garden plants"),
                                        spec("Use", "Fill or amend planting area")
                                });

                        addProduct(productRepository, 5,
                                "Cocopeat Block 5kg",
                                "Compressed coconut-coir growing medium that expands when hydrated. Cocopeat is commonly blended with soil or compost to improve moisture retention and aeration in potting mixes. Rinse or buffer it if the supplied product requires that step, and add nutrients as needed.",
                                "https://images.unsplash.com/photo-1492496913980-501348b61469?auto=format&fit=crop&fm=jpg&q=60&w=800",
                                4.7f, 60, 140, 0,
                                new int[]{1,5,10},
                                new int[]{130,550,1050},
                                new String[][]{
                                        spec("Product Type", "Cocopeat growing medium"),
                                        spec("Base Material", "Coconut coir"),
                                        spec("Form", "Compressed block"),
                                        spec("Preparation", "Hydrate before use"),
                                        spec("Application", "Potting and nursery mixes"),
                                        spec("Nutrients", "Supplement as needed")
                                });

                        addProduct(productRepository, 5,
                                "Compost Organic Mix",
                                "Organic planting amendment made from decomposed materials for mixing into existing garden soil. Mature compost can support soil texture, moisture management, and nutrient cycling. Use alongside appropriate watering and fertilization practices rather than relying on compost alone for every crop.",
                                "https://images.unsplash.com/photo-1492496913980-501348b61469?auto=format&fit=crop&fm=jpg&q=60&w=800",
                                4.8f, 95, 180, 0,
                                new int[]{1,5,10},
                                new int[]{120,500,950},
                                new String[][]{
                                        spec("Product Type", "Organic compost mix"),
                                        spec("Material", "Decomposed organic matter"),
                                        spec("Use", "Soil amendment"),
                                        spec("Application", "Blend into garden soil"),
                                        spec("Soil Benefit", "Supports structure"),
                                        spec("Nutrient Level", "Variable composition")
                                });

                        // Category 6

                        addProduct(productRepository, 6,
                                "Cattle Premium Feed",
                                "Complementary cattle-feed product intended to contribute to a balanced livestock feeding program. Choose feeding quantities according to animal age, body condition, production stage, and the ration already in use. Provide constant access to clean drinking water and consult feed-label directions.",
                                "https://images.unsplash.com/photo-1782836273179-fd735a1a1a5d?auto=format&fit=crop&fm=jpg&q=60&w=800",
                                4.6f, 80, 175, 0,
                                new int[]{1,5,10},
                                new int[]{160,750,1400},
                                new String[][]{
                                        spec("Feed Type", "Cattle feed"),
                                        spec("Target Animal", "Cattle"),
                                        spec("Feed Role", "Part of balanced ration"),
                                        spec("Nutrient Analysis", "Refer to feed label"),
                                        spec("Feeding Rate", "Depends on animal needs"),
                                        spec("Storage", "Cool, dry location")
                                });

                        addProduct(productRepository, 6,
                                "Poultry Starter Feed",
                                "Starter-stage poultry feed intended for young birds during early growth. The appropriate formulation and feeding period depend on the type of poultry and the manufacturer label. Use clean feeders, protect feed from moisture, and make fresh drinking water available throughout the day.",
                                "https://images.unsplash.com/photo-1782836273179-fd735a1a1a5d?auto=format&fit=crop&fm=jpg&q=60&w=800",
                                4.7f, 75, 190, 0,
                                new int[]{1,5,10},
                                new int[]{150,700,1350},
                                new String[][]{
                                        spec("Feed Type", "Starter poultry feed"),
                                        spec("Target Animal", "Young poultry"),
                                        spec("Life Stage", "Starter"),
                                        spec("Nutrient Analysis", "Refer to feed label"),
                                        spec("Feeding Method", "As directed by label"),
                                        spec("Storage", "Keep dry and sealed")
                                });

                        addProduct(productRepository, 6,
                                "Goat Nutritious Feed",
                                "Supplementary feed for goats as part of a diet that also includes suitable forage. Rations should be matched to body weight, age, and physiological stage rather than estimated from package size alone. Introduce dietary changes gradually and ensure water is always available.",
                                "https://images.unsplash.com/photo-1782836273179-fd735a1a1a5d?auto=format&fit=crop&fm=jpg&q=60&w=800",
                                4.5f, 65, 140, 0,
                                new int[]{1,5,10},
                                new int[]{140,650,1200},
                                new String[][]{
                                        spec("Feed Type", "Goat feed"),
                                        spec("Target Animal", "Goats"),
                                        spec("Feed Role", "Supplement to forage"),
                                        spec("Feeding Rate", "Based on animal needs"),
                                        spec("Transition", "Introduce gradually"),
                                        spec("Storage", "Dry, pest-free location")
                                });

                        // Category 7

                        addProduct(productRepository, 7,
                                "Growth Booster Liquid",
                                "Liquid plant-care supplement intended for use within a regular growing and fertilization routine. The appropriate dilution and frequency depend on its ingredient analysis and plant type. Read the packaging before applying to foliage or soil and avoid exceeding the recommended concentration.",
                                "https://images.unsplash.com/photo-1466692476868-aef1dfb1e735?auto=format&fit=crop&fm=jpg&q=60&w=800",
                                4.8f, 90, 260, 0,
                                new int[]{1},
                                new int[]{220},
                                new String[][]{
                                        spec("Product Type", "Liquid plant supplement"),
                                        spec("Form", "Liquid"),
                                        spec("Primary Use", "Plant care"),
                                        spec("Application Route", "Check product label"),
                                        spec("Dilution", "Follow label"),
                                        spec("Suitable Plants", "As specified by manufacturer")
                                });

                        addProduct(productRepository, 7,
                                "Nutrients Plant Formula",
                                "Plant-nutrition product for supplementing essential nutrients where growing conditions call for additional feeding. Suitable amounts depend on the product formula, plant species, and growing medium. Follow the label and avoid stacking multiple nutrient products without checking their combined contents.",
                                "https://images.unsplash.com/photo-1466692476868-aef1dfb1e735?auto=format&fit=crop&fm=jpg&q=60&w=800",
                                4.7f, 80, 210, 0,
                                new int[]{1},
                                new int[]{250},
                                new String[][]{
                                        spec("Product Type", "Plant nutrient formula"),
                                        spec("Use", "Supplement plant nutrition"),
                                        spec("Nutrient Analysis", "Check package label"),
                                        spec("Application", "As directed"),
                                        spec("Plant Compatibility", "Refer to manufacturer"),
                                        spec("Precaution", "Avoid over-fertilization")
                                });

                        addProduct(productRepository, 7,
                                "Protection Leaf Spray",
                                "Leaf-care spray intended for the uses and plant types listed on its packaging. Apply only as directed, ideally after testing on a small portion of sensitive foliage. Avoid spraying under harsh midday sunlight unless the instructions explicitly permit it.",
                                "https://images.unsplash.com/photo-1466692476868-aef1dfb1e735?auto=format&fit=crop&fm=jpg&q=60&w=800",
                                4.6f, 70, 180, 0,
                                new int[]{1},
                                new int[]{230},
                                new String[][]{
                                        spec("Product Type", "Leaf-care spray"),
                                        spec("Application Area", "Plant foliage"),
                                        spec("Purpose", "Label-listed plant care"),
                                        spec("Form", "Spray"),
                                        spec("Compatibility", "Patch-test sensitive plants"),
                                        spec("Directions", "Follow manufacturer label")
                                });

                        // Category 8

                        addProduct(productRepository, 8,
                                "Sensor Soil Monitor",
                                "Soil-monitoring device intended to help gardeners check growing-medium conditions and make more informed watering decisions. Insert the probe according to its instructions and read measurements after allowing them to stabilize. Supported parameters and power requirements depend on the actual model.",
                                "https://images.unsplash.com/photo-1518770660439-4636190af475?auto=format&fit=crop&fm=jpg&q=60&w=800",
                                4.9f, 120, 230, 0,
                                new int[]{1},
                                new int[]{1200},
                                new String[][]{
                                        spec("Equipment Type", "Soil sensor"),
                                        spec("Primary Use", "Soil condition monitoring"),
                                        spec("Measurement", "Check model specification"),
                                        spec("Placement", "Insert probe in soil"),
                                        spec("Power", "Model dependent"),
                                        spec("Care", "Clean probe as directed")
                                });

                        addProduct(productRepository, 8,
                                "Irrigation Drip System",
                                "Drip-irrigation setup designed to deliver water close to plant root zones through tubing and emitters. It can support controlled watering when correctly sized and installed. Regularly check for leaks, clogged emitters, and pressure issues to maintain even distribution.",
                                "https://images.unsplash.com/photo-1518770660439-4636190af475?auto=format&fit=crop&fm=jpg&q=60&w=800",
                                4.8f, 100, 190, 0,
                                new int[]{1},
                                new int[]{1500},
                                new String[][]{
                                        spec("Equipment Type", "Drip irrigation kit"),
                                        spec("Water Delivery", "Drip emitters"),
                                        spec("Primary Use", "Root-zone watering"),
                                        spec("Installation", "Tubing and emitters"),
                                        spec("Maintenance", "Inspect and flush lines"),
                                        spec("Water Source", "Verify pressure compatibility")
                                });

                        addProduct(productRepository, 8,
                                "Monitoring Weather Station",
                                "Weather-monitoring equipment for observing local environmental conditions around a garden or farm. Accurate readings depend on correct placement away from obstructions and heat sources. Check the specific model for supported sensors, connectivity, and power details.",
                                "https://images.unsplash.com/photo-1518770660439-4636190af475?auto=format&fit=crop&fm=jpg&q=60&w=800",
                                4.7f, 75, 140, 0,
                                new int[]{1},
                                new int[]{1800},
                                new String[][]{
                                        spec("Equipment Type", "Weather station"),
                                        spec("Primary Use", "Local weather monitoring"),
                                        spec("Parameters", "Depend on model"),
                                        spec("Installation", "Outdoor suitable placement"),
                                        spec("Power Supply", "Check model specification"),
                                        spec("Maintenance", "Inspect sensors regularly")
                                });

                        // Category 9

                        addProduct(productRepository, 9,
                                "Plastic Garden Pot",
                                "Lightweight plastic plant pot for everyday container gardening. Suitable for growing ornamentals, herbs, and small plants when paired with an appropriately sized root space. Ensure drainage openings are available and use a compatible potting mix to limit waterlogging.",
                                "https://images.unsplash.com/photo-1485955900006-10f4d324d411?auto=format&fit=crop&fm=jpg&q=60&w=800",
                                4.5f, 80, 190, 0,
                                new int[]{1},
                                new int[]{80},
                                new String[][]{
                                        spec("Product Type", "Plant pot"),
                                        spec("Material", "Plastic"),
                                        spec("Placement", "Indoor or sheltered outdoor"),
                                        spec("Use", "Container planting"),
                                        spec("Drainage", "Check for drainage holes"),
                                        spec("Care", "Wash between plantings")
                                });

                        addProduct(productRepository, 9,
                                "Ceramic Decorative Pot",
                                "Decorative ceramic-style planter designed to complement indoor or patio plant displays. Use it as a planting container only when adequate drainage is provided, or place a separate nursery pot inside. Handle carefully to avoid chipping and protect from sudden impacts.",
                                "https://images.unsplash.com/photo-1485955900006-10f4d324d411?auto=format&fit=crop&fm=jpg&q=60&w=800",
                                4.8f, 90, 210, 0,
                                new int[]{1},
                                new int[]{140},
                                new String[][]{
                                        spec("Product Type", "Decorative plant pot"),
                                        spec("Material", "Ceramic"),
                                        spec("Placement", "Indoor or patio"),
                                        spec("Use", "Ornamental planting"),
                                        spec("Drainage", "Verify before direct planting"),
                                        spec("Care", "Handle to avoid chips")
                                });

                        addProduct(productRepository, 9,
                                "Hanging Balcony Pot",
                                "Hanging planter intended for balconies, patios, and other small-space growing areas. Great for trailing ornamentals or compact plants when securely mounted. Use appropriate anchors, account for the weight of wet soil, and water according to plant needs.",
                                "https://images.unsplash.com/photo-1485955900006-10f4d324d411?auto=format&fit=crop&fm=jpg&q=60&w=800",
                                4.6f, 60, 120, 0,
                                new int[]{1},
                                new int[]{110},
                                new String[][]{
                                        spec("Product Type", "Hanging planter"),
                                        spec("Placement", "Balcony or patio"),
                                        spec("Use", "Compact or trailing plants"),
                                        spec("Mounting", "Use secure support"),
                                        spec("Drainage", "Provide proper drainage"),
                                        spec("Safety", "Allow for wet-soil weight")
                                });

                        // Category 10

                        addProduct(productRepository, 10,
                                "Trap Fruit Fly Catcher",
                                "Fruit-fly trapping accessory intended for monitoring or reducing fruit-fly activity in suitable locations. Position it according to the trap instructions and inspect it regularly. Effectiveness depends on the target species, lure choice, and surrounding sanitation practices.",
                                "https://images.unsplash.com/photo-1466721591366-2d5fba72006d?auto=format&fit=crop&fm=jpg&q=60&w=800",
                                4.7f, 80, 190, 0,
                                new int[]{1},
                                new int[]{90},
                                new String[][]{
                                        spec("Product Type", "Insect trap"),
                                        spec("Target Pest", "Fruit flies"),
                                        spec("Control Method", "Trapping"),
                                        spec("Placement", "Follow trap directions"),
                                        spec("Maintenance", "Inspect and replace as needed"),
                                        spec("Supporting Practice", "Remove infested fruit")
                                });

                        addProduct(productRepository, 10,
                                "Repellent Organic Spray",
                                "Pest-repellent spray marketed for plant or garden use. Confirm the ingredients, suitable target pests, and approved application sites before treatment. Apply only according to package instructions and test a small area first where plant sensitivity is uncertain.",
                                "https://images.unsplash.com/photo-1466721591366-2d5fba72006d?auto=format&fit=crop&fm=jpg&q=60&w=800",
                                4.8f, 70, 170, 0,
                                new int[]{1},
                                new int[]{120},
                                new String[][]{
                                        spec("Product Type", "Pest-repellent spray"),
                                        spec("Control Method", "Repellent"),
                                        spec("Ingredients", "Verify on label"),
                                        spec("Target Pests", "Refer to product label"),
                                        spec("Plant Safety", "Check compatibility"),
                                        spec("Application", "Follow package directions")
                                });

                        addProduct(productRepository, 10,
                                "Bait Ant Control",
                                "Ant-bait product intended for locations and ant species indicated by the manufacturer. Baits work by attracting foraging ants, so avoid contaminating bait stations with unrelated sprays. Place only in approved areas and keep away from children, pets, and food.",
                                "https://images.unsplash.com/photo-1466721591366-2d5fba72006d?auto=format&fit=crop&fm=jpg&q=60&w=800",
                                4.5f, 55, 130, 0,
                                new int[]{1},
                                new int[]{100},
                                new String[][]{
                                        spec("Product Type", "Ant bait"),
                                        spec("Target Pest", "Ants"),
                                        spec("Control Method", "Baiting"),
                                        spec("Placement", "Label-approved areas"),
                                        spec("Safety", "Keep from children and pets"),
                                        spec("Application", "Follow product instructions")
                                });

                        // Category 11

                        addProduct(productRepository, 11,
                                "Flowering Rose Plant",
                                "Ornamental rose plant for adding seasonal flowers and fragrance to a bright garden or large container. Provide plenty of sunlight, well-drained soil, and regular watering during establishment. Prune spent blooms and maintain air circulation for healthier growth.",
                                "https://images.unsplash.com/photo-1455659817273-f96807779a8a?auto=format&fit=crop&fm=jpg&q=60&w=800",
                                4.9f, 150, 340, 0,
                                new int[]{1},
                                new int[]{180},
                                new String[][]{
                                        spec("Plant Type", "Flowering ornamental"),
                                        spec("Common Name", "Rose"),
                                        spec("Light", "Full sun"),
                                        spec("Soil", "Fertile, well-drained"),
                                        spec("Watering", "Regular during establishment"),
                                        spec("Maintenance", "Prune and deadhead")
                                });

                        addProduct(productRepository, 11,
                                "Indoor Snake Plant",
                                "Low-maintenance foliage plant appreciated for its upright patterned leaves and adaptability to many indoor environments. Grow in a free-draining potting mix and allow the medium to dry between waterings. Avoid waterlogged soil and place in bright indirect light when possible.",
                                "https://images.unsplash.com/photo-1455659817273-f96807779a8a?auto=format&fit=crop&fm=jpg&q=60&w=800",
                                4.8f, 130, 290, 0,
                                new int[]{1},
                                new int[]{220},
                                new String[][]{
                                        spec("Plant Type", "Indoor foliage plant"),
                                        spec("Common Name", "Snake plant"),
                                        spec("Light", "Indirect light; tolerates low light"),
                                        spec("Soil", "Well-draining potting mix"),
                                        spec("Watering", "Allow soil to dry"),
                                        spec("Care Level", "Low maintenance")
                                });

                        addProduct(productRepository, 11,
                                "Tropical Monstera Plant",
                                "Tropical foliage plant grown for its large, striking leaves and climbing growth habit. Place in bright filtered light and use a loose, well-draining potting mix. Provide support as the plant matures and water when the upper portion of the medium begins to dry.",
                                "https://images.unsplash.com/photo-1455659817273-f96807779a8a?auto=format&fit=crop&fm=jpg&q=60&w=800",
                                4.9f, 140, 310, 5,
                                new int[]{1},
                                new int[]{260},
                                new String[][]{
                                        spec("Plant Type", "Tropical ornamental"),
                                        spec("Common Name", "Monstera"),
                                        spec("Light", "Bright indirect light"),
                                        spec("Soil", "Loose, well-drained mix"),
                                        spec("Watering", "When topsoil starts to dry"),
                                        spec("Growth Habit", "Climbing / spreading")
                                });
                        break;
                }
            } finally {
                executor.shutdown();
            }
        });
    }

    // Always uses the newly generated productID, not an assumed 1..36 ID.
    private static void addProduct(ProductRepository repository,
                                   int categoryID, String productName, String description,
                                   String imageURL, float rating, int totalReviews,
                                   int soldCount, int discountPercentage,
                                   int[] weights, int[] prices, String[][] specifications) {
        Product product = new Product(categoryID, productName, description,
                imageURL, rating, totalReviews, soldCount, discountPercentage);
        int productID = (int) repository.productInsert(product);

        for (int i = 0; i < weights.length; i++) {
            repository.priceInsert(new Price(productID, weights[i], 100, prices[i]));
        }

        List<ProductSpecification> specList = new ArrayList<>();
        for (String[] row : specifications) {
            specList.add(new ProductSpecification(productID, row[0], row[1]));
        }
        repository.specificationInsertAll(specList);
    }

    private static String[] spec(String name, String value) {
        return new String[]{name, value};
    }
}
