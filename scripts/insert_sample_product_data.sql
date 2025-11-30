-- =====================================================
-- ChillGlasses Sample Data Import
-- Generated from crawled data
-- =====================================================

USE chillglasses_db;

SET FOREIGN_KEY_CHECKS = 0;
SET AUTOCOMMIT = 0;
START TRANSACTION;

-- =====================================================
-- Insert Categories
-- =====================================================
TRUNCATE TABLE category;

INSERT INTO category (id, name, slug, description, is_active, display_order, created_at, updated_at) 
VALUES (1, 'Gọng Kính Cận', 'gong-kinh-can', 'Gọng Kính Cận', TRUE, 10, NOW(), NOW());
INSERT INTO category (id, name, slug, description, is_active, display_order, created_at, updated_at) 
VALUES (2, 'Kính Râm', 'kinh-ram', 'Kính Râm', TRUE, 20, NOW(), NOW());
INSERT INTO category (id, name, slug, description, is_active, display_order, created_at, updated_at) 
VALUES (3, 'Tròng Kính', 'trong-kinh', 'Tròng Kính', TRUE, 30, NOW(), NOW());
INSERT INTO category (id, name, slug, description, is_active, display_order, created_at, updated_at) 
VALUES (4, 'Gọng kính mắt mèo', 'gong-kinh-mat-meo', 'Gọng kính mắt mèo', TRUE, 40, NOW(), NOW());
INSERT INTO category (id, name, slug, description, is_active, display_order, created_at, updated_at) 
VALUES (5, 'Gọng kính titan cao cấp', 'gong-kinh-titan-cao-cap', 'Gọng kính titan cao cấp', TRUE, 50, NOW(), NOW());
INSERT INTO category (id, name, slug, description, is_active, display_order, created_at, updated_at) 
VALUES (6, 'Kính Râm Cận', 'kinh-ram-can', 'Kính Râm Cận', TRUE, 60, NOW(), NOW());
INSERT INTO category (id, name, slug, description, is_active, display_order, created_at, updated_at) 
VALUES (7, 'Mẫu Mới Về', 'mau-moi-ve', 'Mẫu Mới Về', TRUE, 70, NOW(), NOW());
INSERT INTO category (id, name, slug, description, is_active, display_order, created_at, updated_at) 
VALUES (8, 'Gọng kính cận nửa viền', 'gong-kinh-can-nua-vien', 'Gọng kính cận nửa viền', TRUE, 80, NOW(), NOW());
INSERT INTO category (id, name, slug, description, is_active, display_order, created_at, updated_at) 
VALUES (9, 'Tròng Cận Thị', 'trong-can-thi', 'Tròng Cận Thị', TRUE, 90, NOW(), NOW());
INSERT INTO category (id, name, slug, description, is_active, display_order, created_at, updated_at) 
VALUES (10, 'Tròng Viễn Thị', 'trong-vien-thi', 'Tròng Viễn Thị', TRUE, 100, NOW(), NOW());
INSERT INTO category (id, name, slug, description, is_active, display_order, created_at, updated_at) 
VALUES (11, 'Tròng Kính Đổi Màu', 'trong-kinh-doi-mau', 'Tròng Kính Đổi Màu', TRUE, 110, NOW(), NOW());

-- =====================================================
-- Insert Products
-- =====================================================
TRUNCATE TABLE product;

INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (1, 'GỌNG KÍNH H31062', 'gong-kinh-h31062-1', 'GỌNG KÍNH H31062 - ChillGlasses. Chất lượng cao, thiết kế hiện đại.', 500000, 552395, 'ChillGlasses', 1, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (2, 'GỌNG KÍNH H70573 – CLUBMASTER CỔ ĐIỂN', 'gong-kinh-h70573-clubmaster-co-dien-2', 'GỌNG KÍNH H70573 – CLUBMASTER CỔ ĐIỂN - Gucci. Chất lượng cao, thiết kế hiện đại.', 550000, 614644, 'Gucci', 1, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (3, 'GỌNG KÍNH REE-MAN 3130', 'gong-kinh-ree-man-3130-3', 'GỌNG KÍNH REE-MAN 3130 - Gucci. Chất lượng cao, thiết kế hiện đại.', 800000, 852964, 'Gucci', 1, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (4, 'GỌNG KÍNH 3650', 'gong-kinh-3650-4', 'GỌNG KÍNH 3650 - Eye Plus. Chất lượng cao, thiết kế hiện đại.', 1180000, 1281351, 'Eye Plus', 1, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (5, 'Gọng Kính Cận Nữ CR4501', 'gong-kinh-can-nu-cr4501-5', 'Gọng Kính Cận Nữ CR4501 - Eye Plus. Chất lượng cao, thiết kế hiện đại.', 450000, 614832, 'Eye Plus', 1, 0, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (6, 'GỌNG KÍNH CLUBMASTER H70839', 'gong-kinh-clubmaster-h70839-6', 'GỌNG KÍNH CLUBMASTER H70839 - ChillGlasses. Chất lượng cao, thiết kế hiện đại.', 550000, 684696, 'ChillGlasses', 1, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (7, 'GỌNG KÍNH ĐA GIÁC - CÀNG KÍNH LÕI THÉP BỌC NHỰA H00216', 'gong-kinh-da-giac-cang-kinh-loi-thep-boc-nhua-h00216-7', 'GỌNG KÍNH ĐA GIÁC - CÀNG KÍNH LÕI THÉP BỌC NHỰA H00216 - ChillGlasses. Chất lượng cao, thiết kế hiện đại.', 550000, 742137, 'ChillGlasses', 1, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (8, 'Gọng Kính Cận Nam KL3502', 'gong-kinh-can-nam-kl3502-8', 'Gọng Kính Cận Nam KL3502 - Gucci. Chất lượng cao, thiết kế hiện đại.', 350000, 474712, 'Gucci', 1, 0, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (9, 'GỌNG KÍNH FORM VUÔNG QUỐC DÂN CÀNG MẢNH 00121', 'gong-kinh-form-vuong-quoc-dan-cang-manh-00121-9', 'GỌNG KÍNH FORM VUÔNG QUỐC DÂN CÀNG MẢNH 00121 - Eye Plus. Chất lượng cao, thiết kế hiện đại.', 450000, 501715, 'Eye Plus', 1, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (10, 'Gọng Kính Cận Reeman RM7506', 'gong-kinh-can-reeman-rm7506-10', 'Gọng Kính Cận Reeman RM7506 - Eye Plus. Chất lượng cao, thiết kế hiện đại.', 750000, 888876, 'Eye Plus', 1, 0, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (11, 'GỌNG KÍNH QUỐC DÂN 3126', 'gong-kinh-quoc-dan-3126-11', 'GỌNG KÍNH QUỐC DÂN 3126 - Gucci. Chất lượng cao, thiết kế hiện đại.', 800000, 941846, 'Gucci', 1, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (12, 'GỌNG KÍNH TRẺ EM HM87276', 'gong-kinh-tre-em-hm87276-12', 'GỌNG KÍNH TRẺ EM HM87276 - ChillGlasses. Chất lượng cao, thiết kế hiện đại.', 350000, 413424, 'ChillGlasses', 1, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (13, 'Gọng Kính Cận Reeman RM7504', 'gong-kinh-can-reeman-rm7504-13', 'Gọng Kính Cận Reeman RM7504 - ChillGlasses. Chất lượng cao, thiết kế hiện đại.', 750000, 925908, 'ChillGlasses', 1, 0, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (14, 'Gọng Kính Cận Unisex TR9003', 'gong-kinh-can-unisex-tr9003-14', 'Gọng Kính Cận Unisex TR9003 - Gucci. Chất lượng cao, thiết kế hiện đại.', 250000, 391463, 'Gucci', 1, 0, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (15, 'Gọng Kính Cận Nữ EP4511', 'gong-kinh-can-nu-ep4511-15', 'Gọng Kính Cận Nữ EP4511 - ChillGlasses. Chất lượng cao, thiết kế hiện đại.', 450000, 539821, 'ChillGlasses', 1, 0, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (16, 'KÍNH CLUBMASTER H70805 LỊCH LÃM', 'kinh-clubmaster-h70805-lich-lam-16', 'KÍNH CLUBMASTER H70805 LỊCH LÃM - ChillGlasses. Chất lượng cao, thiết kế hiện đại.', 550000, 737251, 'ChillGlasses', 1, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (17, 'KÍNH CLUBMASTER H70838', 'kinh-clubmaster-h70838-17', 'KÍNH CLUBMASTER H70838 - ChillGlasses. Chất lượng cao, thiết kế hiện đại.', 550000, 618921, 'ChillGlasses', 1, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (18, 'GỌNG KÍNH 3131', 'gong-kinh-3131-18', 'GỌNG KÍNH 3131 - ChillGlasses. Chất lượng cao, thiết kế hiện đại.', 800000, 883281, 'ChillGlasses', 1, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (19, 'KÍNH RÂM EYE PLUS 18036 C1 GỌNG GHI MẮT ĐEN', 'kinh-ram-eye-plus-18036-c1-gong-ghi-mat-den-19', 'KÍNH RÂM EYE PLUS 18036 C1 GỌNG GHI MẮT ĐEN - Eye Plus. Chất lượng cao, thiết kế hiện đại.', 650000, 724969, 'Eye Plus', 2, 2, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (20, 'Gọng Kính Râm Cận 2in1 TJ8033', 'gong-kinh-ram-can-2in1-tj8033-20', 'Gọng Kính Râm Cận 2in1 TJ8033 - ChillGlasses. Chất lượng cao, thiết kế hiện đại.', 450000, 557362, 'ChillGlasses', 6, 0, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (21, 'KÍNH RÂM EYE PLUS 5188 C1 GỌNG GHI MẮT ĐEN', 'kinh-ram-eye-plus-5188-c1-gong-ghi-mat-den-21', 'KÍNH RÂM EYE PLUS 5188 C1 GỌNG GHI MẮT ĐEN - ChillGlasses. Chất lượng cao, thiết kế hiện đại.', 550000, 607087, 'ChillGlasses', 2, 289, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (22, 'Kính Râm Nam Titan Plabra R01', 'kinh-ram-nam-titan-plabra-r01-22', 'Kính Râm Nam Titan Plabra R01 - ChillGlasses. Chất lượng cao, thiết kế hiện đại.', 950000, 1126947, 'ChillGlasses', 2, 0, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (23, 'KÍNH RÂM EYE PLUS 5188 C5 GỌNG BẠC MẮT KHÓI', 'kinh-ram-eye-plus-5188-c5-gong-bac-mat-khoi-23', 'KÍNH RÂM EYE PLUS 5188 C5 GỌNG BẠC MẮT KHÓI - ChillGlasses. Chất lượng cao, thiết kế hiện đại.', 550000, 611633, 'ChillGlasses', 2, 13, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (24, 'KÍNH RÂM EYEPLUS 7260 C3 MẮT HỒNG', 'kinh-ram-eyeplus-7260-c3-mat-hong-24', 'KÍNH RÂM EYEPLUS 7260 C3 MẮT HỒNG - ChillGlasses. Chất lượng cao, thiết kế hiện đại.', 780000, 904175, 'ChillGlasses', 2, 3, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (25, 'Kính Râm EYEPLUS 2231 C1 ĐEN NHÁM', 'kinh-ram-eyeplus-2231-c1-den-nham-25', 'Kính Râm EYEPLUS 2231 C1 ĐEN NHÁM - ChillGlasses. Chất lượng cao, thiết kế hiện đại.', 350000, 462361, 'ChillGlasses', 2, 1897, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (26, 'Gọng Kính Râm Cận 2in1 TJ8022', 'gong-kinh-ram-can-2in1-tj8022-26', 'Gọng Kính Râm Cận 2in1 TJ8022 - Gucci. Chất lượng cao, thiết kế hiện đại.', 380000, 432588, 'Gucci', 6, 0, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (27, 'KÍNH RÂM EYEPLUS 9078 C1 GỌNG ĐEN MẮT ĐEN', 'kinh-ram-eyeplus-9078-c1-gong-den-mat-den-27', 'KÍNH RÂM EYEPLUS 9078 C1 GỌNG ĐEN MẮT ĐEN - Gucci. Chất lượng cao, thiết kế hiện đại.', 750000, 849058, 'Gucci', 2, 16, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (28, 'KÍNH RÂM EYE PLUS 2245', 'kinh-ram-eye-plus-2245-28', 'KÍNH RÂM EYE PLUS 2245 - Eye Plus. Chất lượng cao, thiết kế hiện đại.', 350000, 456313, 'Eye Plus', 2, 37, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (29, 'Kính Râm Nam Vân Gỗ EP1100', 'kinh-ram-nam-van-go-ep1100-29', 'Kính Râm Nam Vân Gỗ EP1100 - Gucci. Chất lượng cao, thiết kế hiện đại.', 850000, 1009487, 'Gucci', 2, 0, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (30, 'KÍNH RÂM EYEPLUS 016 C1 GỌNG ĐEN MẮT ĐEN', 'kinh-ram-eyeplus-016-c1-gong-den-mat-den-30', 'KÍNH RÂM EYEPLUS 016 C1 GỌNG ĐEN MẮT ĐEN - Eye Plus. Chất lượng cao, thiết kế hiện đại.', 400000, 595752, 'Eye Plus', 2, 1, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (31, 'Kính Râm Nam Vân Gỗ R03', 'kinh-ram-nam-van-go-r03-31', 'Kính Râm Nam Vân Gỗ R03 - Gucci. Chất lượng cao, thiết kế hiện đại.', 850000, 905982, 'Gucci', 2, 0, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (32, 'KÍNH RÂM EYE PLUS 634 C2 MẮT ĐEN', 'kinh-ram-eye-plus-634-c2-mat-den-32', 'KÍNH RÂM EYE PLUS 634 C2 MẮT ĐEN - ChillGlasses. Chất lượng cao, thiết kế hiện đại.', 550000, 609900, 'ChillGlasses', 2, 1, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (33, 'Gọng Kính Râm Cận 2in1 TJ8023', 'gong-kinh-ram-can-2in1-tj8023-33', 'Gọng Kính Râm Cận 2in1 TJ8023 - Eye Plus. Chất lượng cao, thiết kế hiện đại.', 380000, 509968, 'Eye Plus', 6, 0, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (34, 'KÍNH RÂM EYEPLUS 20258', 'kinh-ram-eyeplus-20258-34', 'KÍNH RÂM EYEPLUS 20258 - Eye Plus. Chất lượng cao, thiết kế hiện đại.', 400000, 548960, 'Eye Plus', 2, 1, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (35, 'KÍNH RÂM EYEPLUS 9078 C4', 'kinh-ram-eyeplus-9078-c4-35', 'KÍNH RÂM EYEPLUS 9078 C4 - ChillGlasses. Chất lượng cao, thiết kế hiện đại.', 750000, 880860, 'ChillGlasses', 2, 15, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (36, 'KÍNH RÂM EYE PLUS 8562 - GỌNG ĐEN NHÁM MẮT ĐEN', 'kinh-ram-eye-plus-8562-gong-den-nham-mat-den-36', 'KÍNH RÂM EYE PLUS 8562 - GỌNG ĐEN NHÁM MẮT ĐEN - ChillGlasses. Chất lượng cao, thiết kế hiện đại.', 300000, 399271, 'ChillGlasses', 2, 659, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (37, 'TRÒNG KÍNH TRONG SUỐT VƯỢT TRỘI ESSILOR CRIZAL SAPPHIRE HR 1.59', 'trong-kinh-trong-suot-vuot-troi-essilor-crizal-sapphire-hr-159-37', 'TRÒNG KÍNH TRONG SUỐT VƯỢT TRỘI ESSILOR CRIZAL SAPPHIRE HR 1.59 - ChillGlasses. Chất lượng cao, thiết kế hiện đại.', 2980000, 3100711, 'ChillGlasses', 3, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (38, 'Tròng kính Đa tròng Hoga 1.56 Progressive HC', 'trong-kinh-da-trong-hoga-156-progressive-hc-38', 'Tròng kính Đa tròng Hoga 1.56 Progressive HC - Gucci. Chất lượng cao, thiết kế hiện đại.', 500000, 639143, 'Gucci', 3, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (39, 'Tròng Siêu Mỏng Lọc Ánh Sáng Xanh 1.60 U6 Chemi Hàn Quốc', 'trong-sieu-mong-loc-anh-sang-xanh-160-u6-chemi-han-quoc-39', 'Tròng Siêu Mỏng Lọc Ánh Sáng Xanh 1.60 U6 Chemi Hàn Quốc - ChillGlasses. Chất lượng cao, thiết kế hiện đại.', 690000, 803992, 'ChillGlasses', 3, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (40, 'TRÒNG KÍNH CHEMI X DRIVE 1.60 ASP', 'trong-kinh-chemi-x-drive-160-asp-40', 'TRÒNG KÍNH CHEMI X DRIVE 1.60 ASP - Gucci. Chất lượng cao, thiết kế hiện đại.', 900000, 1032168, 'Gucci', 3, 23, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (41, 'Hộp 100 Khăn Nano Đa Năng', 'hop-100-khan-nano-da-nang-41', 'Hộp 100 Khăn Nano Đa Năng - Eye Plus. Chất lượng cao, thiết kế hiện đại.', 50000, 243177, 'Eye Plus', 1, 12803, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (42, 'Kính Đổi Màu Chống Ánh Sáng Xanh Zinmy 1.60 AS Duramax', 'kinh-doi-mau-chong-anh-sang-xanh-zinmy-160-as-duramax-42', 'Kính Đổi Màu Chống Ánh Sáng Xanh Zinmy 1.60 AS Duramax - ChillGlasses. Chất lượng cao, thiết kế hiện đại.', 1800000, 1982752, 'ChillGlasses', 2, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (43, 'Tròng Siêu Mỏng Lọc Ánh Sáng Xanh 1.74 U6 Chemi Hàn Quốc', 'trong-sieu-mong-loc-anh-sang-xanh-174-u6-chemi-han-quoc-43', 'Tròng Siêu Mỏng Lọc Ánh Sáng Xanh 1.74 U6 Chemi Hàn Quốc - Gucci. Chất lượng cao, thiết kế hiện đại.', 2950000, 3025972, 'Gucci', 3, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (44, 'Tròng Kính Đổi Màu Element 1.61 AS', 'trong-kinh-doi-mau-element-161-as-44', 'Tròng Kính Đổi Màu Element 1.61 AS - Gucci. Chất lượng cao, thiết kế hiện đại.', 1780000, 1957763, 'Gucci', 11, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (45, 'Tròng Kính Đa Tròng Hoga 1.56 Progressive Blue Cut Free Form SHMC', 'trong-kinh-da-trong-hoga-156-progressive-blue-cut-free-form-shmc-45', 'Tròng Kính Đa Tròng Hoga 1.56 Progressive Blue Cut Free Form SHMC - ChillGlasses. Chất lượng cao, thiết kế hiện đại.', 1100000, 1259259, 'ChillGlasses', 3, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (46, 'Tròng kính đa tròng Hoya 1.56 Progressive Photo Grey SHMC', 'trong-kinh-da-trong-hoya-156-progressive-photo-grey-shmc-46', 'Tròng kính đa tròng Hoya 1.56 Progressive Photo Grey SHMC - Eye Plus. Chất lượng cao, thiết kế hiện đại.', 1350000, 1436514, 'Eye Plus', 3, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (47, 'Kính Đổi Màu Chống Ánh Sáng Xanh HOGA Sapphire 1.56', 'kinh-doi-mau-chong-anh-sang-xanh-hoga-sapphire-156-47', 'Kính Đổi Màu Chống Ánh Sáng Xanh HOGA Sapphire 1.56 - Gucci. Chất lượng cao, thiết kế hiện đại.', 750000, 838833, 'Gucci', 1, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (48, 'TRÒNG KÍNH ĐỔI MÀU 3 IN 1 CHEMI 1.67 MR-7 ASP BLUE-BLOCK', 'trong-kinh-doi-mau-3-in-1-chemi-167-mr-7-asp-blue-block-48', 'TRÒNG KÍNH ĐỔI MÀU 3 IN 1 CHEMI 1.67 MR-7 ASP BLUE-BLOCK - ChillGlasses. Chất lượng cao, thiết kế hiện đại.', 2400000, 2569072, 'ChillGlasses', 11, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (49, 'GỌNG KÍNH CẬN THỜI TRANG BLANCY DÁNG MẮT MÈO 0934 C1 ĐEN BÓNG', 'gong-kinh-can-thoi-trang-blancy-dang-mat-meo-0934-c1-den-bong-49', 'GỌNG KÍNH CẬN THỜI TRANG BLANCY DÁNG MẮT MÈO 0934 C1 ĐEN BÓNG - ChillGlasses. Chất lượng cao, thiết kế hiện đại.', 550000, 660376, 'ChillGlasses', 4, 48, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (50, 'GỌNG KÍNH CẬN NHỰA CAO CẤP DÁNG MẮT MÈO EYEPLUS 8887 C26 TRONG', 'gong-kinh-can-nhua-cao-cap-dang-mat-meo-eyeplus-8887-c26-trong-50', 'GỌNG KÍNH CẬN NHỰA CAO CẤP DÁNG MẮT MÈO EYEPLUS 8887 C26 TRONG - Eye Plus. Chất lượng cao, thiết kế hiện đại.', 350000, 440434, 'Eye Plus', 3, 1, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (51, 'GỌNG KÍNH EYEPLUS 8241', 'gong-kinh-eyeplus-8241-51', 'GỌNG KÍNH EYEPLUS 8241 - Eye Plus. Chất lượng cao, thiết kế hiện đại.', 550000, 736663, 'Eye Plus', 1, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (52, 'GỌNG KÍNH CẬN THỜI TRANG BLANCY DÁNG MẮT MÈO 0934 C66 XÁM', 'gong-kinh-can-thoi-trang-blancy-dang-mat-meo-0934-c66-xam-52', 'GỌNG KÍNH CẬN THỜI TRANG BLANCY DÁNG MẮT MÈO 0934 C66 XÁM - Gucci. Chất lượng cao, thiết kế hiện đại.', 550000, 698749, 'Gucci', 4, 1, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (53, 'GỌNG KÍNH REEMAN 98359', 'gong-kinh-reeman-98359-53', 'GỌNG KÍNH REEMAN 98359 - Gucci. Chất lượng cao, thiết kế hiện đại.', 1080000, 1227323, 'Gucci', 1, 13, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (54, 'GỌNG KÍNH TF 70119', 'gong-kinh-tf-70119-54', 'GỌNG KÍNH TF 70119 - Gucci. Chất lượng cao, thiết kế hiện đại.', 950000, 1095001, 'Gucci', 1, 11, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (55, 'GỌNG KÍNH REEMAN 0455', 'gong-kinh-reeman-0455-55', 'GỌNG KÍNH REEMAN 0455 - Gucci. Chất lượng cao, thiết kế hiện đại.', 1380000, 1473767, 'Gucci', 1, 40, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (56, 'GỌNG KÍNH REEMAN 3522', 'gong-kinh-reeman-3522-56', 'GỌNG KÍNH REEMAN 3522 - Eye Plus. Chất lượng cao, thiết kế hiện đại.', 1080000, 1191311, 'Eye Plus', 1, 134, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (57, 'GỌNG KÍNH REEMAN 1175', 'gong-kinh-reeman-1175-57', 'GỌNG KÍNH REEMAN 1175 - ChillGlasses. Chất lượng cao, thiết kế hiện đại.', 1380000, 1451109, 'ChillGlasses', 1, 19, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (58, 'GỌNG KÍNH EYEPLUS 30125', 'gong-kinh-eyeplus-30125-58', 'GỌNG KÍNH EYEPLUS 30125 - Eye Plus. Chất lượng cao, thiết kế hiện đại.', 750000, 856855, 'Eye Plus', 1, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (59, 'GỌNG KÍNH REEMAN 0015 - GHI', 'gong-kinh-reeman-0015-ghi-59', 'GỌNG KÍNH REEMAN 0015 - GHI - Gucci. Chất lượng cao, thiết kế hiện đại.', 1580000, 1678501, 'Gucci', 1, 72, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (60, 'GỌNG KÍNH REEMAN 0015 - BẠC', 'gong-kinh-reeman-0015-bac-60', 'GỌNG KÍNH REEMAN 0015 - BẠC - Gucci. Chất lượng cao, thiết kế hiện đại.', 1580000, 1691422, 'Gucci', 1, 48, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (61, 'GỌNG KÍNH EYEPLUS 00321', 'gong-kinh-eyeplus-00321-61', 'GỌNG KÍNH EYEPLUS 00321 - ChillGlasses. Chất lượng cao, thiết kế hiện đại.', 680000, 763765, 'ChillGlasses', 1, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (62, 'GỌNG KÍNH REEMAN 80984', 'gong-kinh-reeman-80984-62', 'GỌNG KÍNH REEMAN 80984 - Eye Plus. Chất lượng cao, thiết kế hiện đại.', 1380000, 1574017, 'Eye Plus', 1, 13, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (63, 'GỌNG KÍNH REEMAN 2482', 'gong-kinh-reeman-2482-63', 'GỌNG KÍNH REEMAN 2482 - Eye Plus. Chất lượng cao, thiết kế hiện đại.', 1580000, 1715101, 'Eye Plus', 1, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (64, 'GỌNG KÍNH TF 70093', 'gong-kinh-tf-70093-64', 'GỌNG KÍNH TF 70093 - Gucci. Chất lượng cao, thiết kế hiện đại.', 950000, 1041807, 'Gucci', 1, 16, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (65, 'GỌNG KÍNH RÂM CẬN 2IN1 TJ21779 C1 ĐEN', 'gong-kinh-ram-can-2in1-tj21779-c1-den-65', 'GỌNG KÍNH RÂM CẬN 2IN1 TJ21779 C1 ĐEN - Gucci. Chất lượng cao, thiết kế hiện đại.', 450000, 628056, 'Gucci', 6, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (66, 'GỌNG KÍNH RÂM CẬN 18070', 'gong-kinh-ram-can-18070-66', 'GỌNG KÍNH RÂM CẬN 18070 - Eye Plus. Chất lượng cao, thiết kế hiện đại.', 550000, 623911, 'Eye Plus', 6, 49, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (67, 'GỌNG KÍNH RÂM CẬN 2IN1 2211 C2 GỌNG NÂU MẮT NÂU', 'gong-kinh-ram-can-2in1-2211-c2-gong-nau-mat-nau-67', 'GỌNG KÍNH RÂM CẬN 2IN1 2211 C2 GỌNG NÂU MẮT NÂU - Eye Plus. Chất lượng cao, thiết kế hiện đại.', 450000, 531197, 'Eye Plus', 6, 3, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (68, 'GỌNG KÍNH RÂM CẬN 2IN1 KIM LOẠI 1007 C2 GỌNG VÀNG MẮT TÍM', 'gong-kinh-ram-can-2in1-kim-loai-1007-c2-gong-vang-mat-tim-68', 'GỌNG KÍNH RÂM CẬN 2IN1 KIM LOẠI 1007 C2 GỌNG VÀNG MẮT TÍM - Gucci. Chất lượng cao, thiết kế hiện đại.', 450000, 574997, 'Gucci', 6, 1, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (69, 'GỌNG KÍNH RÂM CẬN 2IN1 18021 C3 ĐEN BÓNG', 'gong-kinh-ram-can-2in1-18021-c3-den-bong-69', 'GỌNG KÍNH RÂM CẬN 2IN1 18021 C3 ĐEN BÓNG - Gucci. Chất lượng cao, thiết kế hiện đại.', 450000, 509561, 'Gucci', 6, 1, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (70, 'Gọng Kính Râm Cận Clubmaster 2in1 TJ8020', 'gong-kinh-ram-can-clubmaster-2in1-tj8020-70', 'Gọng Kính Râm Cận Clubmaster 2in1 TJ8020 - ChillGlasses. Chất lượng cao, thiết kế hiện đại.', 380000, 487054, 'ChillGlasses', 6, 0, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (71, 'Gọng Kính Râm Cận 2in1 TJ012', 'gong-kinh-ram-can-2in1-tj012-71', 'Gọng Kính Râm Cận 2in1 TJ012 - ChillGlasses. Chất lượng cao, thiết kế hiện đại.', 450000, 549964, 'ChillGlasses', 6, 0, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (72, 'GỌNG KÍNH RÂM CẬN 2IN1 22440 C1 GỌNG ĐEN MẮT ĐEN', 'gong-kinh-ram-can-2in1-22440-c1-gong-den-mat-den-72', 'GỌNG KÍNH RÂM CẬN 2IN1 22440 C1 GỌNG ĐEN MẮT ĐEN - Gucci. Chất lượng cao, thiết kế hiện đại.', 450000, 537880, 'Gucci', 6, 4, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (73, 'GỌNG KÍNH RÂM CẬN 2IN1 90114 C2 ĐEN', 'gong-kinh-ram-can-2in1-90114-c2-den-73', 'GỌNG KÍNH RÂM CẬN 2IN1 90114 C2 ĐEN - ChillGlasses. Chất lượng cao, thiết kế hiện đại.', 450000, 543230, 'ChillGlasses', 6, 1, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (74, 'Gọng Kính Râm Cận 2in1 TJ8023', 'gong-kinh-ram-can-2in1-tj8023-74', 'Gọng Kính Râm Cận 2in1 TJ8023 - Eye Plus. Chất lượng cao, thiết kế hiện đại.', 380000, 485702, 'Eye Plus', 6, 0, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (75, 'GỌNG KÍNH RÂM CẬN 2IN1 222005 C4 XÁM', 'gong-kinh-ram-can-2in1-222005-c4-xam-75', 'GỌNG KÍNH RÂM CẬN 2IN1 222005 C4 XÁM - Gucci. Chất lượng cao, thiết kế hiện đại.', 450000, 611871, 'Gucci', 6, 22, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (76, 'GỌNG KÍNH RÂM CẬN 2IN1 TJ21779 C1 ĐEN', 'gong-kinh-ram-can-2in1-tj21779-c1-den-76', 'GỌNG KÍNH RÂM CẬN 2IN1 TJ21779 C1 ĐEN - ChillGlasses. Chất lượng cao, thiết kế hiện đại.', 450000, 555669, 'ChillGlasses', 6, 1, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (77, 'Gọng Kính Râm Cận 2in1 TJ011', 'gong-kinh-ram-can-2in1-tj011-77', 'Gọng Kính Râm Cận 2in1 TJ011 - Gucci. Chất lượng cao, thiết kế hiện đại.', 350000, 486908, 'Gucci', 6, 0, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (78, 'Gọng Kính Râm Cận 2in1 TJ8036', 'gong-kinh-ram-can-2in1-tj8036-78', 'Gọng Kính Râm Cận 2in1 TJ8036 - Eye Plus. Chất lượng cao, thiết kế hiện đại.', 650000, 787535, 'Eye Plus', 6, 0, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (79, 'GỌNG KÍNH RÂM CẬN 2IN1 22440 C4 GỌNG NÂU MẮT CAM', 'gong-kinh-ram-can-2in1-22440-c4-gong-nau-mat-cam-79', 'GỌNG KÍNH RÂM CẬN 2IN1 22440 C4 GỌNG NÂU MẮT CAM - Gucci. Chất lượng cao, thiết kế hiện đại.', 450000, 552943, 'Gucci', 6, 5, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (80, 'GỌNG KÍNH RÂM CẬN 2IN1 222002 C2 ĐEN NHÁM', 'gong-kinh-ram-can-2in1-222002-c2-den-nham-80', 'GỌNG KÍNH RÂM CẬN 2IN1 222002 C2 ĐEN NHÁM - ChillGlasses. Chất lượng cao, thiết kế hiện đại.', 450000, 535250, 'ChillGlasses', 6, 2, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (81, 'GỌNG KÍNH RÂM CẬN 2IN1 22440 C3 GỌNG XÁM MẮT HỒNG', 'gong-kinh-ram-can-2in1-22440-c3-gong-xam-mat-hong-81', 'GỌNG KÍNH RÂM CẬN 2IN1 22440 C3 GỌNG XÁM MẮT HỒNG - Gucci. Chất lượng cao, thiết kế hiện đại.', 450000, 589415, 'Gucci', 6, 8, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (82, 'GỌNG KÍNH 68165 – GỌNG KHOAN ĐÍNH CHARM', 'gong-kinh-68165-gong-khoan-dinh-charm-82', 'GỌNG KÍNH 68165 – GỌNG KHOAN ĐÍNH CHARM - ChillGlasses. Chất lượng cao, thiết kế hiện đại.', 950000, 1017972, 'ChillGlasses', 1, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (83, 'GỌNG KÍNH REEMAN ACETATE 120 C1 ĐEN', 'gong-kinh-reeman-acetate-120-c1-den-83', 'GỌNG KÍNH REEMAN ACETATE 120 C1 ĐEN - Gucci. Chất lượng cao, thiết kế hiện đại.', 950000, 1105137, 'Gucci', 1, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (84, 'GỌNG KÍNH REEMAN ACETATE 643 C81 ĐỒI MỒI', 'gong-kinh-reeman-acetate-643-c81-doi-moi-84', 'GỌNG KÍNH REEMAN ACETATE 643 C81 ĐỒI MỒI - Gucci. Chất lượng cao, thiết kế hiện đại.', 950000, 1054803, 'Gucci', 1, 18, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (85, 'GỌNG KÍNH CẬN BLANCY 6779 C7 ĐEN BÓNG VIỀN BẠC', 'gong-kinh-can-blancy-6779-c7-den-bong-vien-bac-85', 'GỌNG KÍNH CẬN BLANCY 6779 C7 ĐEN BÓNG VIỀN BẠC - ChillGlasses. Chất lượng cao, thiết kế hiện đại.', 550000, 625120, 'ChillGlasses', 1, 2, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (86, 'GỌNG KÍNH CẬN EYEPLUS 2505 C6 XÁM', 'gong-kinh-can-eyeplus-2505-c6-xam-86', 'GỌNG KÍNH CẬN EYEPLUS 2505 C6 XÁM - Gucci. Chất lượng cao, thiết kế hiện đại.', 220000, 285193, 'Gucci', 1, 372, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (87, 'GỌNG KÍNH CẬN BLANCY 15115 C53 ĐEN BẠC', 'gong-kinh-can-blancy-15115-c53-den-bac-87', 'GỌNG KÍNH CẬN BLANCY 15115 C53 ĐEN BẠC - Gucci. Chất lượng cao, thiết kế hiện đại.', 680000, 830464, 'Gucci', 1, 2, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (88, 'GỌNG KÍNH CẬN EYEPLUS 2505 C2 ĐEN NHÁM', 'gong-kinh-can-eyeplus-2505-c2-den-nham-88', 'GỌNG KÍNH CẬN EYEPLUS 2505 C2 ĐEN NHÁM - Eye Plus. Chất lượng cao, thiết kế hiện đại.', 220000, 418667, 'Eye Plus', 1, 2552, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (89, 'GỌNG KÍNH REEMAN ACETATE 524 C25 ĐỒI MỒI', 'gong-kinh-reeman-acetate-524-c25-doi-moi-89', 'GỌNG KÍNH REEMAN ACETATE 524 C25 ĐỒI MỒI - Eye Plus. Chất lượng cao, thiết kế hiện đại.', 750000, 912752, 'Eye Plus', 1, 1, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (90, 'GỌNG KÍNH CẬN BLANCY 7184 C2 ĐEN BÓNG VIỀN BẠC', 'gong-kinh-can-blancy-7184-c2-den-bong-vien-bac-90', 'GỌNG KÍNH CẬN BLANCY 7184 C2 ĐEN BÓNG VIỀN BẠC - Gucci. Chất lượng cao, thiết kế hiện đại.', 550000, 678975, 'Gucci', 1, 1, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (91, 'GỌNG KÍNH CẬN BLANCY CLUB MASTER 14250 C11 ĐỒI MỒI', 'gong-kinh-can-blancy-club-master-14250-c11-doi-moi-91', 'GỌNG KÍNH CẬN BLANCY CLUB MASTER 14250 C11 ĐỒI MỒI - Eye Plus. Chất lượng cao, thiết kế hiện đại.', 550000, 619306, 'Eye Plus', 1, 2, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (92, 'GỌNG KÍNH CẬN BLANCY 6779 C8 ĐEN BÓNG VIỀN GHI', 'gong-kinh-can-blancy-6779-c8-den-bong-vien-ghi-92', 'GỌNG KÍNH CẬN BLANCY 6779 C8 ĐEN BÓNG VIỀN GHI - ChillGlasses. Chất lượng cao, thiết kế hiện đại.', 550000, 678222, 'ChillGlasses', 1, 1, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (93, 'GỌNG KÍNH REEMAN ACETATE 524 C12 RÊU', 'gong-kinh-reeman-acetate-524-c12-reu-93', 'GỌNG KÍNH REEMAN ACETATE 524 C12 RÊU - ChillGlasses. Chất lượng cao, thiết kế hiện đại.', 750000, 887065, 'ChillGlasses', 1, 1, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (94, 'GỌNG KÍNH CẬN BLANCY 20226 C2 ĐEN VIỀN BẠC', 'gong-kinh-can-blancy-20226-c2-den-vien-bac-94', 'GỌNG KÍNH CẬN BLANCY 20226 C2 ĐEN VIỀN BẠC - Eye Plus. Chất lượng cao, thiết kế hiện đại.', 550000, 722450, 'Eye Plus', 1, 1, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (95, 'GỌNG KÍNH CẬN BLANCY DÁNG CLUB MASTER 8902 C1/1 ĐEN BẠC', 'gong-kinh-can-blancy-dang-club-master-8902-c11-den-bac-95', 'GỌNG KÍNH CẬN BLANCY DÁNG CLUB MASTER 8902 C1/1 ĐEN BẠC - ChillGlasses. Chất lượng cao, thiết kế hiện đại.', 650000, 717029, 'ChillGlasses', 1, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (96, 'GỌNG KÍNH REEMAN 32048 C1 ĐEN VIỀN BẠC', 'gong-kinh-reeman-32048-c1-den-vien-bac-96', 'GỌNG KÍNH REEMAN 32048 C1 ĐEN VIỀN BẠC - Gucci. Chất lượng cao, thiết kế hiện đại.', 1380000, 1506139, 'Gucci', 1, 3, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (97, 'GỌNG KÍNH EYEPLUS 7226', 'gong-kinh-eyeplus-7226-97', 'GỌNG KÍNH EYEPLUS 7226 - ChillGlasses. Chất lượng cao, thiết kế hiện đại.', 580000, 740030, 'ChillGlasses', 1, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (98, 'GỌNG KÍNH CẬN MAYBLANC 1013 C1 ĐEN ĐỎ', 'gong-kinh-can-mayblanc-1013-c1-den-do-98', 'GỌNG KÍNH CẬN MAYBLANC 1013 C1 ĐEN ĐỎ - Eye Plus. Chất lượng cao, thiết kế hiện đại.', 750000, 835247, 'Eye Plus', 1, 2, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (99, 'GỌNG KÍNH CẬN EYE PLUS CLUB MASTER 5102 C3 GHI GỌNG ĐEN', 'gong-kinh-can-eye-plus-club-master-5102-c3-ghi-gong-den-99', 'GỌNG KÍNH CẬN EYE PLUS CLUB MASTER 5102 C3 GHI GỌNG ĐEN - Eye Plus. Chất lượng cao, thiết kế hiện đại.', 360000, 537579, 'Eye Plus', 1, 2, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (100, 'GỌNG KÍNH REEMAN 3518 C1 ĐEN VIỀN GHI', 'gong-kinh-reeman-3518-c1-den-vien-ghi-100', 'GỌNG KÍNH REEMAN 3518 C1 ĐEN VIỀN GHI - Gucci. Chất lượng cao, thiết kế hiện đại.', 950000, 1018296, 'Gucci', 1, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (101, 'GỌNG KÍNH CẬN BLANCY TITANIUM 89060 C355 ĐEN VIỀN GHI', 'gong-kinh-can-blancy-titanium-89060-c355-den-vien-ghi-101', 'GỌNG KÍNH CẬN BLANCY TITANIUM 89060 C355 ĐEN VIỀN GHI - Eye Plus. Chất lượng cao, thiết kế hiện đại.', 680000, 792596, 'Eye Plus', 5, 1, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (102, 'GỌNG KÍNH CẬN BLANCY 17762 C2 ĐEN VIỀN BẠC', 'gong-kinh-can-blancy-17762-c2-den-vien-bac-102', 'GỌNG KÍNH CẬN BLANCY 17762 C2 ĐEN VIỀN BẠC - Eye Plus. Chất lượng cao, thiết kế hiện đại.', 550000, 748317, 'Eye Plus', 1, 1, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (103, 'GỌNG KÍNH CẬN BLANCY 70772 C2 ĐEN BÓNG', 'gong-kinh-can-blancy-70772-c2-den-bong-103', 'GỌNG KÍNH CẬN BLANCY 70772 C2 ĐEN BÓNG - Gucci. Chất lượng cao, thiết kế hiện đại.', 550000, 650427, 'Gucci', 1, 1, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (104, 'GỌNG KÍNH TF 70093', 'gong-kinh-tf-70093-104', 'GỌNG KÍNH TF 70093 - Gucci. Chất lượng cao, thiết kế hiện đại.', 950000, 1086366, 'Gucci', 1, 16, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (105, 'GỌNG KÍNH CẬN BLANCY TITANIUM 89072 C455 ĐEN BẠC', 'gong-kinh-can-blancy-titanium-89072-c455-den-bac-105', 'GỌNG KÍNH CẬN BLANCY TITANIUM 89072 C455 ĐEN BẠC - Eye Plus. Chất lượng cao, thiết kế hiện đại.', 680000, 862597, 'Eye Plus', 5, 15, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (106, 'Tròng Kính Đổi Màu Vision X 1.60 AS SUN PROTECT CHỐNG ÁNH SÁNG XANH', 'trong-kinh-doi-mau-vision-x-160-as-sun-protect-chong-anh-sang-xanh-106', 'Tròng Kính Đổi Màu Vision X 1.60 AS SUN PROTECT CHỐNG ÁNH SÁNG XANH - Gucci. Chất lượng cao, thiết kế hiện đại.', 1150000, 1200025, 'Gucci', 11, 12, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (107, 'Tròng Siêu Mỏng Lọc Ánh Sáng Xanh 1.60 U6 Chemi Hàn Quốc', 'trong-sieu-mong-loc-anh-sang-xanh-160-u6-chemi-han-quoc-107', 'Tròng Siêu Mỏng Lọc Ánh Sáng Xanh 1.60 U6 Chemi Hàn Quốc - Eye Plus. Chất lượng cao, thiết kế hiện đại.', 690000, 760705, 'Eye Plus', 3, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (108, 'Tròng Đổi Màu Essilor Transition Gen 8 STYLE COLORS 1.60 AS', 'trong-doi-mau-essilor-transition-gen-8-style-colors-160-as-108', 'Tròng Đổi Màu Essilor Transition Gen 8 STYLE COLORS 1.60 AS - Eye Plus. Chất lượng cao, thiết kế hiện đại.', 5680000, 5756680, 'Eye Plus', 11, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (109, 'Tròng Đổi Màu Essilor Transition Gen 8 STYLE COLORS 1.50 SPH STYLE COLORS', 'trong-doi-mau-essilor-transition-gen-8-style-colors-150-sph-style-colors-109', 'Tròng Đổi Màu Essilor Transition Gen 8 STYLE COLORS 1.50 SPH STYLE COLORS - ChillGlasses. Chất lượng cao, thiết kế hiện đại.', 3990000, 4045276, 'ChillGlasses', 11, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (110, 'Kính Đổi Màu Chống Ánh Sáng Xanh Zinmy 1.60 AS Duramax', 'kinh-doi-mau-chong-anh-sang-xanh-zinmy-160-as-duramax-110', 'Kính Đổi Màu Chống Ánh Sáng Xanh Zinmy 1.60 AS Duramax - ChillGlasses. Chất lượng cao, thiết kế hiện đại.', 1800000, 1937954, 'ChillGlasses', 2, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (111, 'Tròng kính 1.56 - HOGA 1.56 UV400 SHMC- PLUS', 'trong-kinh-156-hoga-156-uv400-shmc-plus-111', 'Tròng kính 1.56 - HOGA 1.56 UV400 SHMC- PLUS - Gucci. Chất lượng cao, thiết kế hiện đại.', 250000, 416170, 'Gucci', 3, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (112, 'Tròng Siêu Mỏng 1.67 U6 Chemi Hàn Quốc', 'trong-sieu-mong-167-u6-chemi-han-quoc-112', 'Tròng Siêu Mỏng 1.67 U6 Chemi Hàn Quốc - ChillGlasses. Chất lượng cao, thiết kế hiện đại.', 1090000, 1163789, 'ChillGlasses', 3, 4999, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (113, 'Tròng Siêu Mỏng Lọc Ánh Sáng Xanh 1.74 U6 Chemi Hàn Quốc', 'trong-sieu-mong-loc-anh-sang-xanh-174-u6-chemi-han-quoc-113', 'Tròng Siêu Mỏng Lọc Ánh Sáng Xanh 1.74 U6 Chemi Hàn Quốc - Eye Plus. Chất lượng cao, thiết kế hiện đại.', 2950000, 3070216, 'Eye Plus', 3, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (114, 'Tròng Kính Đổi Màu Element 1.61 AS', 'trong-kinh-doi-mau-element-161-as-114', 'Tròng Kính Đổi Màu Element 1.61 AS - Gucci. Chất lượng cao, thiết kế hiện đại.', 1780000, 1887470, 'Gucci', 11, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (115, 'Tròng Siêu Mỏng Essilor Crizal Prevencia chiết suất 1.67', 'trong-sieu-mong-essilor-crizal-prevencia-chiet-suat-167-115', 'Tròng Siêu Mỏng Essilor Crizal Prevencia chiết suất 1.67 - Gucci. Chất lượng cao, thiết kế hiện đại.', 3480000, 3532775, 'Gucci', 3, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (116, 'Tròng Siêu Mỏng 1.67 HPV Hoya Nhật Bản', 'trong-sieu-mong-167-hpv-hoya-nhat-ban-116', 'Tròng Siêu Mỏng 1.67 HPV Hoya Nhật Bản - ChillGlasses. Chất lượng cao, thiết kế hiện đại.', 2500000, 2664956, 'ChillGlasses', 3, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (117, 'TRÒNG KÍNH ĐỔI MÀU 3 IN 1 CHEMI 1.67 MR-7 ASP BLUE-BLOCK', 'trong-kinh-doi-mau-3-in-1-chemi-167-mr-7-asp-blue-block-117', 'TRÒNG KÍNH ĐỔI MÀU 3 IN 1 CHEMI 1.67 MR-7 ASP BLUE-BLOCK - Eye Plus. Chất lượng cao, thiết kế hiện đại.', 2400000, 2534138, 'Eye Plus', 11, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (118, 'Tròng Đổi Màu Essilor Classic 1.56', 'trong-doi-mau-essilor-classic-156-118', 'Tròng Đổi Màu Essilor Classic 1.56 - Eye Plus. Chất lượng cao, thiết kế hiện đại.', 1870000, 1997769, 'Eye Plus', 11, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (119, 'Tròng Kính Đổi Màu Vision X 1.60 AS SUN PROTECT CHỐNG ÁNH SÁNG XANH', 'trong-kinh-doi-mau-vision-x-160-as-sun-protect-chong-anh-sang-xanh-119', 'Tròng Kính Đổi Màu Vision X 1.60 AS SUN PROTECT CHỐNG ÁNH SÁNG XANH - Eye Plus. Chất lượng cao, thiết kế hiện đại.', 1150000, 1242186, 'Eye Plus', 11, 12, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (120, 'Tròng Đổi Màu Essilor Transition Gen 8 STYLE COLORS 1.60 AS', 'trong-doi-mau-essilor-transition-gen-8-style-colors-160-as-120', 'Tròng Đổi Màu Essilor Transition Gen 8 STYLE COLORS 1.60 AS - ChillGlasses. Chất lượng cao, thiết kế hiện đại.', 5680000, 5802662, 'ChillGlasses', 11, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (121, 'Tròng Kính Đổi Màu Element 1.67', 'trong-kinh-doi-mau-element-167-121', 'Tròng Kính Đổi Màu Element 1.67 - Eye Plus. Chất lượng cao, thiết kế hiện đại.', 2230000, 2367049, 'Eye Plus', 11, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (122, 'Tròng Kính Chemi Crystal U2 Coated 1.60', 'trong-kinh-chemi-crystal-u2-coated-160-122', 'Tròng Kính Chemi Crystal U2 Coated 1.60 - Eye Plus. Chất lượng cao, thiết kế hiện đại.', 500000, 684082, 'Eye Plus', 3, 4470, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (123, 'Tròng Đổi Màu Essilor Transition Gen 8 STYLE COLORS 1.50 SPH STYLE COLORS', 'trong-doi-mau-essilor-transition-gen-8-style-colors-150-sph-style-colors-123', 'Tròng Đổi Màu Essilor Transition Gen 8 STYLE COLORS 1.50 SPH STYLE COLORS - ChillGlasses. Chất lượng cao, thiết kế hiện đại.', 3990000, 4081355, 'ChillGlasses', 11, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (124, 'Tròng Đổi Màu Essilor Transition Gen 8 1.59 AS Airwear Màu Khói', 'trong-doi-mau-essilor-transition-gen-8-159-as-airwear-mau-khoi-124', 'Tròng Đổi Màu Essilor Transition Gen 8 1.59 AS Airwear Màu Khói - ChillGlasses. Chất lượng cao, thiết kế hiện đại.', 6180000, 6351390, 'ChillGlasses', 11, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (125, 'Kính Đổi Màu Chống Ánh Sáng Xanh Zinmy 1.60 AS Duramax', 'kinh-doi-mau-chong-anh-sang-xanh-zinmy-160-as-duramax-125', 'Kính Đổi Màu Chống Ánh Sáng Xanh Zinmy 1.60 AS Duramax - ChillGlasses. Chất lượng cao, thiết kế hiện đại.', 1800000, 1908622, 'ChillGlasses', 2, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (126, 'Tròng kính 1.56 - HOGA 1.56 UV400 SHMC- PLUS', 'trong-kinh-156-hoga-156-uv400-shmc-plus-126', 'Tròng kính 1.56 - HOGA 1.56 UV400 SHMC- PLUS - Eye Plus. Chất lượng cao, thiết kế hiện đại.', 250000, 363383, 'Eye Plus', 3, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (127, 'Tròng Kính Đổi Màu Cận Viễn Hoga Hàn Quốc', 'trong-kinh-doi-mau-can-vien-hoga-han-quoc-127', 'Tròng Kính Đổi Màu Cận Viễn Hoga Hàn Quốc - Gucci. Chất lượng cao, thiết kế hiện đại.', 450000, 507220, 'Gucci', 11, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (128, 'Tròng Kính Đổi Màu Element 1.61 AS', 'trong-kinh-doi-mau-element-161-as-128', 'Tròng Kính Đổi Màu Element 1.61 AS - ChillGlasses. Chất lượng cao, thiết kế hiện đại.', 1780000, 1938883, 'ChillGlasses', 11, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (129, 'Tròng Siêu Mỏng Essilor Crizal Prevencia chiết suất 1.67', 'trong-sieu-mong-essilor-crizal-prevencia-chiet-suat-167-129', 'Tròng Siêu Mỏng Essilor Crizal Prevencia chiết suất 1.67 - Gucci. Chất lượng cao, thiết kế hiện đại.', 3480000, 3664038, 'Gucci', 3, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (130, 'Tròng Kính Đổi Màu Chemi 1.56 Hàn Quốc', 'trong-kinh-doi-mau-chemi-156-han-quoc-130', 'Tròng Kính Đổi Màu Chemi 1.56 Hàn Quốc - Eye Plus. Chất lượng cao, thiết kế hiện đại.', 700000, 801795, 'Eye Plus', 11, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (131, 'Tròng Đổi Màu Essilor Classic 1.56', 'trong-doi-mau-essilor-classic-156-131', 'Tròng Đổi Màu Essilor Classic 1.56 - Eye Plus. Chất lượng cao, thiết kế hiện đại.', 1870000, 2063030, 'Eye Plus', 11, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (132, 'Tròng Kính Đổi Màu Vision X 1.60 AS SUN PROTECT CHỐNG ÁNH SÁNG XANH', 'trong-kinh-doi-mau-vision-x-160-as-sun-protect-chong-anh-sang-xanh-132', 'Tròng Kính Đổi Màu Vision X 1.60 AS SUN PROTECT CHỐNG ÁNH SÁNG XANH - Gucci. Chất lượng cao, thiết kế hiện đại.', 1150000, 1225416, 'Gucci', 11, 12, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (133, 'Tròng Đổi Màu Essilor Transition Gen 8 STYLE COLORS 1.60 AS', 'trong-doi-mau-essilor-transition-gen-8-style-colors-160-as-133', 'Tròng Đổi Màu Essilor Transition Gen 8 STYLE COLORS 1.60 AS - ChillGlasses. Chất lượng cao, thiết kế hiện đại.', 5680000, 5746827, 'ChillGlasses', 11, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (134, 'Tròng Kính Đổi Màu Element 1.67', 'trong-kinh-doi-mau-element-167-134', 'Tròng Kính Đổi Màu Element 1.67 - Gucci. Chất lượng cao, thiết kế hiện đại.', 2230000, 2370071, 'Gucci', 11, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (135, 'Tròng Đổi Màu Essilor Transition Gen 8 STYLE COLORS 1.50 SPH STYLE COLORS', 'trong-doi-mau-essilor-transition-gen-8-style-colors-150-sph-style-colors-135', 'Tròng Đổi Màu Essilor Transition Gen 8 STYLE COLORS 1.50 SPH STYLE COLORS - Eye Plus. Chất lượng cao, thiết kế hiện đại.', 3990000, 4103231, 'Eye Plus', 11, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (136, 'Tròng Đổi Màu Essilor Transition Gen 8 1.59 AS Airwear Màu Khói', 'trong-doi-mau-essilor-transition-gen-8-159-as-airwear-mau-khoi-136', 'Tròng Đổi Màu Essilor Transition Gen 8 1.59 AS Airwear Màu Khói - Eye Plus. Chất lượng cao, thiết kế hiện đại.', 6180000, 6285704, 'Eye Plus', 11, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (137, 'Kính Đổi Màu Chống Ánh Sáng Xanh Zinmy 1.60 AS Duramax', 'kinh-doi-mau-chong-anh-sang-xanh-zinmy-160-as-duramax-137', 'Kính Đổi Màu Chống Ánh Sáng Xanh Zinmy 1.60 AS Duramax - Gucci. Chất lượng cao, thiết kế hiện đại.', 1800000, 1926298, 'Gucci', 2, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (138, 'Tròng Kính Đổi Màu Cận Viễn Hoga Hàn Quốc', 'trong-kinh-doi-mau-can-vien-hoga-han-quoc-138', 'Tròng Kính Đổi Màu Cận Viễn Hoga Hàn Quốc - Gucci. Chất lượng cao, thiết kế hiện đại.', 450000, 545120, 'Gucci', 11, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (139, 'Tròng Kính Đổi Màu Element 1.61 AS', 'trong-kinh-doi-mau-element-161-as-139', 'Tròng Kính Đổi Màu Element 1.61 AS - Gucci. Chất lượng cao, thiết kế hiện đại.', 1780000, 1922117, 'Gucci', 11, 10, 0.00, 0, TRUE, NOW(), NOW());
INSERT INTO product (id, name, slug, description, price, original_price, brand, category_id, stock_quantity, rating, review_count, is_active, created_at, updated_at) 
VALUES (140, 'Kính Đổi Màu Chống Ánh Sáng Xanh HOGA Sapphire 1.56', 'kinh-doi-mau-chong-anh-sang-xanh-hoga-sapphire-156-140', 'Kính Đổi Màu Chống Ánh Sáng Xanh HOGA Sapphire 1.56 - Eye Plus. Chất lượng cao, thiết kế hiện đại.', 750000, 802378, 'Eye Plus', 1, 10, 0.00, 0, TRUE, NOW(), NOW());

-- =====================================================
-- Insert Product Images
-- =====================================================
TRUNCATE TABLE product_image;

INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (1, 1, 'https://kinhmateyeplus.com/wp-content/uploads/2025/08/IMG_1677.jpg', 'GỌNG KÍNH H31062', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (2, 1, 'https://kinhmateyeplus.com/wp-content/uploads/2025/08/IMG_1678.jpg', 'GỌNG KÍNH H31062', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (3, 1, 'https://kinhmateyeplus.com/wp-content/uploads/2025/08/IMG_1680.jpg', 'GỌNG KÍNH H31062', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (4, 1, 'https://kinhmateyeplus.com/wp-content/uploads/2025/08/IMG_1566.jpg', 'GỌNG KÍNH H31062', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (5, 1, 'https://kinhmateyeplus.com/wp-content/uploads/2025/08/IMG_1620.jpg', 'GỌNG KÍNH H31062', FALSE, 4, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (6, 2, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9722.jpg', 'GỌNG KÍNH H70573 – CLUBMASTER CỔ ĐIỂN', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (7, 2, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9718.jpg', 'GỌNG KÍNH H70573 – CLUBMASTER CỔ ĐIỂN', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (8, 2, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0264.jpg', 'GỌNG KÍNH H70573 – CLUBMASTER CỔ ĐIỂN', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (9, 2, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_3284.jpg', 'GỌNG KÍNH H70573 – CLUBMASTER CỔ ĐIỂN', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (10, 2, 'https://kinhmateyeplus.com/wp-content/uploads/2025/08/image.jpg', 'GỌNG KÍNH H70573 – CLUBMASTER CỔ ĐIỂN', FALSE, 4, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (11, 3, 'https://kinhmateyeplus.com/wp-content/uploads/2025/10/IMG_8965.jpg', 'GỌNG KÍNH REE-MAN 3130', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (12, 3, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9722.jpg', 'GỌNG KÍNH REE-MAN 3130', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (13, 3, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_8638.jpg', 'GỌNG KÍNH REE-MAN 3130', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (14, 3, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/DSCF1574.jpg', 'GỌNG KÍNH REE-MAN 3130', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (15, 4, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_8638.jpg', 'GỌNG KÍNH 3650', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (16, 4, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0376.jpg', 'GỌNG KÍNH 3650', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (17, 4, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/den-bong.jpg', 'GỌNG KÍNH 3650', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (18, 4, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0264.jpg', 'GỌNG KÍNH 3650', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (19, 4, 'https://kinhmateyeplus.com/wp-content/uploads/2025/10/3650-c1.jpg', 'GỌNG KÍNH 3650', FALSE, 4, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (20, 5, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/281120241732795644.jpeg', 'Gọng Kính Cận Nữ CR4501', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (21, 5, 'https://kinhmateyeplus.com/wp-content/uploads/2020/04/9-13-700x700-1.jpg', 'Gọng Kính Cận Nữ CR4501', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (22, 5, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/Layer-3-copy-2.png', 'Gọng Kính Cận Nữ CR4501', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (23, 5, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9208.jpg', 'Gọng Kính Cận Nữ CR4501', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (24, 6, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/Layer-3-copy-2.png', 'GỌNG KÍNH CLUBMASTER H70839', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (25, 6, 'https://kinhmateyeplus.com/wp-content/uploads/2025/08/Background3.jpg', 'GỌNG KÍNH CLUBMASTER H70839', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (26, 6, 'https://kinhmateyeplus.com/wp-content/uploads/2025/08/IMG_1685.jpg', 'GỌNG KÍNH CLUBMASTER H70839', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (27, 6, 'https://kinhmateyeplus.com/wp-content/uploads/2025/08/IMG_1684.jpg', 'GỌNG KÍNH CLUBMASTER H70839', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (28, 7, 'https://kinhmateyeplus.com/wp-content/uploads/2025/08/00216-c4.jpg', 'GỌNG KÍNH ĐA GIÁC - CÀNG KÍNH LÕI THÉP BỌC NHỰA H00216', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (29, 7, 'https://kinhmateyeplus.com/wp-content/uploads/2025/08/00216-c5.jpg', 'GỌNG KÍNH ĐA GIÁC - CÀNG KÍNH LÕI THÉP BỌC NHỰA H00216', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (30, 7, 'https://kinhmateyeplus.com/wp-content/uploads/2025/08/00216-c3.jpg', 'GỌNG KÍNH ĐA GIÁC - CÀNG KÍNH LÕI THÉP BỌC NHỰA H00216', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (31, 8, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/281120241732795634.jpeg', 'Gọng Kính Cận Nam KL3502', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (32, 8, 'https://kinhmateyeplus.com/wp-content/uploads/2020/04/29-15-700x700-1.jpg', 'Gọng Kính Cận Nam KL3502', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (33, 8, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/Layer-3-copy-2.png', 'Gọng Kính Cận Nam KL3502', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (34, 8, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9682.jpg', 'Gọng Kính Cận Nam KL3502', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (35, 9, 'https://kinhmateyeplus.com/wp-content/uploads/2025/10/IMG_0886.jpg', 'GỌNG KÍNH FORM VUÔNG QUỐC DÂN CÀNG MẢNH 00121', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (36, 9, 'https://kinhmateyeplus.com/wp-content/uploads/2025/10/IMG_0888.jpg', 'GỌNG KÍNH FORM VUÔNG QUỐC DÂN CÀNG MẢNH 00121', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (37, 9, 'https://kinhmateyeplus.com/wp-content/uploads/2025/10/IMG_0885.jpg', 'GỌNG KÍNH FORM VUÔNG QUỐC DÂN CÀNG MẢNH 00121', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (38, 9, 'https://kinhmateyeplus.com/wp-content/uploads/2025/10/IMG_0887.jpg', 'GỌNG KÍNH FORM VUÔNG QUỐC DÂN CÀNG MẢNH 00121', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (39, 9, 'https://kinhmateyeplus.com/wp-content/uploads/2025/10/IMG_0889.jpg', 'GỌNG KÍNH FORM VUÔNG QUỐC DÂN CÀNG MẢNH 00121', FALSE, 4, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (40, 10, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/281120241732795601.jpeg', 'Gọng Kính Cận Reeman RM7506', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (41, 10, 'https://kinhmateyeplus.com/wp-content/uploads/2020/04/33-11-700x700-1.jpg', 'Gọng Kính Cận Reeman RM7506', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (42, 10, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0125.jpg', 'Gọng Kính Cận Reeman RM7506', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (43, 10, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0687.jpg', 'Gọng Kính Cận Reeman RM7506', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (44, 11, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/Layer-3-copy-2.png', 'GỌNG KÍNH QUỐC DÂN 3126', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (45, 11, 'https://kinhmateyeplus.com/wp-content/uploads/2025/10/3126-c105.jpg', 'GỌNG KÍNH QUỐC DÂN 3126', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (46, 11, 'https://kinhmateyeplus.com/wp-content/uploads/2025/10/IMG_8924.jpg', 'GỌNG KÍNH QUỐC DÂN 3126', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (47, 11, 'https://kinhmateyeplus.com/wp-content/uploads/2025/10/IMG_8964.jpg', 'GỌNG KÍNH QUỐC DÂN 3126', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (48, 12, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/Layer-3-copy-2.png', 'GỌNG KÍNH TRẺ EM HM87276', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (49, 12, 'https://kinhmateyeplus.com/wp-content/uploads/2025/08/87276-c1.jpg', 'GỌNG KÍNH TRẺ EM HM87276', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (50, 12, 'https://kinhmateyeplus.com/wp-content/uploads/2025/08/87276-c3.jpg', 'GỌNG KÍNH TRẺ EM HM87276', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (51, 13, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/281120241732795640.jpeg', 'Gọng Kính Cận Reeman RM7504', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (52, 13, 'https://kinhmateyeplus.com/wp-content/uploads/2020/04/17-13-700x700-1.jpg', 'Gọng Kính Cận Reeman RM7504', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (53, 13, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0140.jpg', 'Gọng Kính Cận Reeman RM7504', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (54, 13, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0135.jpg', 'Gọng Kính Cận Reeman RM7504', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (55, 14, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/281120241732795601.jpeg', 'Gọng Kính Cận Unisex TR9003', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (56, 14, 'https://kinhmateyeplus.com/wp-content/uploads/2020/04/9-12-700x700-1.jpg', 'Gọng Kính Cận Unisex TR9003', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (57, 14, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9208.jpg', 'Gọng Kính Cận Unisex TR9003', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (58, 14, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_3284.jpg', 'Gọng Kính Cận Unisex TR9003', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (59, 15, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/Layer-3-copy-2.png', 'Gọng Kính Cận Nữ EP4511', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (60, 15, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0140.jpg', 'Gọng Kính Cận Nữ EP4511', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (61, 15, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9685.jpg', 'Gọng Kính Cận Nữ EP4511', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (62, 15, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0135.jpg', 'Gọng Kính Cận Nữ EP4511', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (63, 15, 'https://kinhmateyeplus.com/wp-content/uploads/2020/04/38-10.jpg', 'Gọng Kính Cận Nữ EP4511', FALSE, 4, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (64, 16, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/Layer-3-copy-2.png', 'KÍNH CLUBMASTER H70805 LỊCH LÃM', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (65, 16, 'https://kinhmateyeplus.com/wp-content/uploads/2025/08/Background2.jpg', 'KÍNH CLUBMASTER H70805 LỊCH LÃM', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (66, 16, 'https://kinhmateyeplus.com/wp-content/uploads/2025/08/IMG_1683.jpg', 'KÍNH CLUBMASTER H70805 LỊCH LÃM', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (67, 16, 'https://kinhmateyeplus.com/wp-content/uploads/2025/08/IMG_1682.jpg', 'KÍNH CLUBMASTER H70805 LỊCH LÃM', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (68, 17, 'https://kinhmateyeplus.com/wp-content/uploads/2025/08/70838-c5.jpg', 'KÍNH CLUBMASTER H70838', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (69, 17, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0135.jpg', 'KÍNH CLUBMASTER H70838', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (70, 17, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/den-bong.jpg', 'KÍNH CLUBMASTER H70838', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (71, 17, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_3284.jpg', 'KÍNH CLUBMASTER H70838', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (72, 18, 'https://kinhmateyeplus.com/wp-content/uploads/2025/10/IMG_8928.jpg', 'GỌNG KÍNH 3131', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (73, 18, 'https://kinhmateyeplus.com/wp-content/uploads/2025/10/IMG_8966.jpg', 'GỌNG KÍNH 3131', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (74, 18, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9208.jpg', 'GỌNG KÍNH 3131', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (75, 19, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/es-trans-gen-8-1.50.jpg', 'KÍNH RÂM EYE PLUS 18036 C1 GỌNG GHI MẮT ĐEN', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (76, 19, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/es-trans-gen-8-KHOI.jpg', 'KÍNH RÂM EYE PLUS 18036 C1 GỌNG GHI MẮT ĐEN', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (77, 19, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9693-1.jpg', 'KÍNH RÂM EYE PLUS 18036 C1 GỌNG GHI MẮT ĐEN', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (78, 19, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9741-1.jpg', 'KÍNH RÂM EYE PLUS 18036 C1 GỌNG GHI MẮT ĐEN', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (79, 19, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9725-1.jpg', 'KÍNH RÂM EYE PLUS 18036 C1 GỌNG GHI MẮT ĐEN', FALSE, 4, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (80, 20, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/8033-gong-den-bac-mat-xanh-2-e1570431670675.jpg', 'Gọng Kính Râm Cận 2in1 TJ8033', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (81, 20, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/8033-gong-den-bac-mat-xanh-3-e1570431720462.jpg', 'Gọng Kính Râm Cận 2in1 TJ8033', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (82, 20, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/8033-gong-den-bac-mat-xanh-4-e1570431753586.jpg', 'Gọng Kính Râm Cận 2in1 TJ8033', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (83, 20, 'https://kinhmateyeplus.com/wp-content/uploads/2019/10/8033-gọng-đen-bạc-mắt-xanh-web.jpg', 'Gọng Kính Râm Cận 2in1 TJ8033', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (84, 20, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/281120241732795692.jpeg', 'Gọng Kính Râm Cận 2in1 TJ8033', FALSE, 4, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (85, 21, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/es-trans-gen-8-1.50.jpg', 'KÍNH RÂM EYE PLUS 5188 C1 GỌNG GHI MẮT ĐEN', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (86, 21, 'https://kinhmateyeplus.com/wp-content/uploads/2023/06/Group-4-copy-3.jpg', 'KÍNH RÂM EYE PLUS 5188 C1 GỌNG GHI MẮT ĐEN', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (87, 21, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/trong-element.jpg', 'KÍNH RÂM EYE PLUS 5188 C1 GỌNG GHI MẮT ĐEN', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (88, 21, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9687-1.jpg', 'KÍNH RÂM EYE PLUS 5188 C1 GỌNG GHI MẮT ĐEN', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (89, 21, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9704-1.jpg', 'KÍNH RÂM EYE PLUS 5188 C1 GỌNG GHI MẮT ĐEN', FALSE, 4, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (90, 22, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/64303750_2227852474002191_3408197372763701248_o-e1563953586456.jpg', 'Kính Râm Nam Titan Plabra R01', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (91, 22, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0780-1.jpg', 'Kính Râm Nam Titan Plabra R01', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (92, 22, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0804-1.jpg', 'Kính Râm Nam Titan Plabra R01', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (93, 23, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/es-trans-gen-8-1.50.jpg', 'KÍNH RÂM EYE PLUS 5188 C5 GỌNG BẠC MẮT KHÓI', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (94, 23, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9697-1.jpg', 'KÍNH RÂM EYE PLUS 5188 C5 GỌNG BẠC MẮT KHÓI', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (95, 23, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9714-1.jpg', 'KÍNH RÂM EYE PLUS 5188 C5 GỌNG BẠC MẮT KHÓI', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (96, 23, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9729-1.jpg', 'KÍNH RÂM EYE PLUS 5188 C5 GỌNG BẠC MẮT KHÓI', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (97, 23, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9765-1.jpg', 'KÍNH RÂM EYE PLUS 5188 C5 GỌNG BẠC MẮT KHÓI', FALSE, 4, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (98, 24, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0801-1.jpg', 'KÍNH RÂM EYEPLUS 7260 C3 MẮT HỒNG', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (99, 24, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0777-1.jpg', 'KÍNH RÂM EYEPLUS 7260 C3 MẮT HỒNG', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (100, 24, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0829-1.jpg', 'KÍNH RÂM EYEPLUS 7260 C3 MẮT HỒNG', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (101, 24, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0866-1.jpg', 'KÍNH RÂM EYEPLUS 7260 C3 MẮT HỒNG', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (102, 24, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_2118.jpg', 'KÍNH RÂM EYEPLUS 7260 C3 MẮT HỒNG', FALSE, 4, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (103, 25, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/trong-element.jpg', 'Kính Râm EYEPLUS 2231 C1 ĐEN NHÁM', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (104, 25, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0801-1.jpg', 'Kính Râm EYEPLUS 2231 C1 ĐEN NHÁM', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (105, 25, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_2119.jpg', 'Kính Râm EYEPLUS 2231 C1 ĐEN NHÁM', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (106, 25, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_2132.jpg', 'Kính Râm EYEPLUS 2231 C1 ĐEN NHÁM', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (107, 25, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_2155.jpg', 'Kính Râm EYEPLUS 2231 C1 ĐEN NHÁM', FALSE, 4, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (108, 26, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/8022-vien-xam-bac-mat-xanh-1-e1570430253916.jpg', 'Gọng Kính Râm Cận 2in1 TJ8022', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (109, 26, 'https://kinhmateyeplus.com/wp-content/uploads/2019/10/8022-viền-xám-bạc-mắt-xanh-web.jpg', 'Gọng Kính Râm Cận 2in1 TJ8022', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (110, 26, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/281120241732795689.jpeg', 'Gọng Kính Râm Cận 2in1 TJ8022', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (111, 26, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9682.jpg', 'Gọng Kính Râm Cận 2in1 TJ8022', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (112, 27, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/es-trans-gen-8-1.50.jpg', 'KÍNH RÂM EYEPLUS 9078 C1 GỌNG ĐEN MẮT ĐEN', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (113, 27, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0780-1.jpg', 'KÍNH RÂM EYEPLUS 9078 C1 GỌNG ĐEN MẮT ĐEN', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (114, 27, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0777-1.jpg', 'KÍNH RÂM EYEPLUS 9078 C1 GỌNG ĐEN MẮT ĐEN', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (115, 27, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/9078-c1.jpg', 'KÍNH RÂM EYEPLUS 9078 C1 GỌNG ĐEN MẮT ĐEN', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (116, 28, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/Untitled-1-copy-2.png', 'KÍNH RÂM EYE PLUS 2245', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (117, 28, 'https://kinhmateyeplus.com/wp-content/uploads/2025/01/IMG_0848.jpg', 'KÍNH RÂM EYE PLUS 2245', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (118, 28, 'https://kinhmateyeplus.com/wp-content/uploads/2025/01/IMG_0872.jpg', 'KÍNH RÂM EYE PLUS 2245', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (119, 28, 'https://kinhmateyeplus.com/wp-content/uploads/2025/01/IMG_0827.jpg', 'KÍNH RÂM EYE PLUS 2245', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (120, 29, 'https://kinhmateyeplus.com/wp-content/uploads/2019/07/67124150_2282357851884986_977763358067392512_n-768x768-1.jpg', 'Kính Râm Nam Vân Gỗ EP1100', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (121, 29, 'https://kinhmateyeplus.com/wp-content/uploads/2019/06/DSC04871-e1562905482269.jpg', 'Kính Râm Nam Vân Gỗ EP1100', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (122, 29, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/es-trans-gen-8-KHOI.jpg', 'Kính Râm Nam Vân Gỗ EP1100', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (123, 29, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0872-1.jpg', 'Kính Râm Nam Vân Gỗ EP1100', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (124, 30, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0872-1.jpg', 'KÍNH RÂM EYEPLUS 016 C1 GỌNG ĐEN MẮT ĐEN', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (125, 30, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0866-1.jpg', 'KÍNH RÂM EYEPLUS 016 C1 GỌNG ĐEN MẮT ĐEN', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (126, 30, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0801-1.jpg', 'KÍNH RÂM EYEPLUS 016 C1 GỌNG ĐEN MẮT ĐEN', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (127, 30, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/7-copy.jpg', 'KÍNH RÂM EYEPLUS 016 C1 GỌNG ĐEN MẮT ĐEN', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (128, 30, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/12-10.jpg', 'KÍNH RÂM EYEPLUS 016 C1 GỌNG ĐEN MẮT ĐEN', FALSE, 4, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (129, 31, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/DSC05372-e1563422834427.jpg', 'Kính Râm Nam Vân Gỗ R03', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (130, 31, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/Untitled-1-copy-2.png', 'Kính Râm Nam Vân Gỗ R03', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (131, 31, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0848.jpg', 'Kính Râm Nam Vân Gỗ R03', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (132, 32, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/es-trans-gen-8-1.50.jpg', 'KÍNH RÂM EYE PLUS 634 C2 MẮT ĐEN', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (133, 32, 'https://kinhmateyeplus.com/wp-content/uploads/2017/09/20250506-095454.jpg', 'KÍNH RÂM EYE PLUS 634 C2 MẮT ĐEN', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (134, 32, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0862-2.jpg', 'KÍNH RÂM EYE PLUS 634 C2 MẮT ĐEN', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (135, 32, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0874-2.jpg', 'KÍNH RÂM EYE PLUS 634 C2 MẮT ĐEN', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (136, 32, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0885-2.jpg', 'KÍNH RÂM EYE PLUS 634 C2 MẮT ĐEN', FALSE, 4, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (137, 33, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/8023-vien-vang-mat-den-3-e1570430763913.jpg', 'Gọng Kính Râm Cận 2in1 TJ8023', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (138, 33, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/8023-vien-vang-mat-den-4-e1570430821877.jpg', 'Gọng Kính Râm Cận 2in1 TJ8023', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (139, 33, 'https://kinhmateyeplus.com/wp-content/uploads/2019/10/8023-viền-vàng-mắt-đen-1-e1570430665864.jpg', 'Gọng Kính Râm Cận 2in1 TJ8023', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (140, 34, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/es-trans-gen-8-1.50.jpg', 'KÍNH RÂM EYEPLUS 20258', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (141, 34, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0850-1.jpg', 'KÍNH RÂM EYEPLUS 20258', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (142, 34, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0825-1.jpg', 'KÍNH RÂM EYEPLUS 20258', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (143, 34, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/17-copy.jpg', 'KÍNH RÂM EYEPLUS 20258', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (144, 34, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/10-7.jpg', 'KÍNH RÂM EYEPLUS 20258', FALSE, 4, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (145, 35, 'https://kinhmateyeplus.com/wp-content/uploads/2024/07/Group-4-copy-11.jpg', 'KÍNH RÂM EYEPLUS 9078 C4', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (146, 35, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0850-1.jpg', 'KÍNH RÂM EYEPLUS 9078 C4', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (147, 35, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0872-1.jpg', 'KÍNH RÂM EYEPLUS 9078 C4', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (148, 35, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_8457.jpg', 'KÍNH RÂM EYEPLUS 9078 C4', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (149, 35, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/9078-c4.jpg', 'KÍNH RÂM EYEPLUS 9078 C4', FALSE, 4, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (150, 36, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/trong-element.jpg', 'KÍNH RÂM EYE PLUS 8562 - GỌNG ĐEN NHÁM MẮT ĐEN', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (151, 36, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9901-1.png', 'KÍNH RÂM EYE PLUS 8562 - GỌNG ĐEN NHÁM MẮT ĐEN', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (152, 36, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0777-1.jpg', 'KÍNH RÂM EYE PLUS 8562 - GỌNG ĐEN NHÁM MẮT ĐEN', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (153, 36, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/Layer-1-4.png', 'KÍNH RÂM EYE PLUS 8562 - GỌNG ĐEN NHÁM MẮT ĐEN', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (154, 37, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/es-trans-gen-8-1.50.jpg', 'TRÒNG KÍNH TRONG SUỐT VƯỢT TRỘI ESSILOR CRIZAL SAPPHIRE HR 1.59', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (155, 37, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/crizal-sapphire.png', 'TRÒNG KÍNH TRONG SUỐT VƯỢT TRỘI ESSILOR CRIZAL SAPPHIRE HR 1.59', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (156, 37, 'https://kinhmateyeplus.com/wp-content/uploads/2022/10/SAPPHIRE-CRIZAL.png', 'TRÒNG KÍNH TRONG SUỐT VƯỢT TRỘI ESSILOR CRIZAL SAPPHIRE HR 1.59', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (157, 37, 'https://kinhmateyeplus.com/wp-content/uploads/2017/09/20250506-095454.jpg', 'TRÒNG KÍNH TRONG SUỐT VƯỢT TRỘI ESSILOR CRIZAL SAPPHIRE HR 1.59', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (158, 38, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/313499213_500279415477602_6065368677006869224_n.png', 'Tròng kính Đa tròng Hoga 1.56 Progressive HC', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (159, 38, 'https://kinhmateyeplus.com/wp-content/uploads/2024/10/Hoga-1.56-Progressive-HC-1024x1024.jpg', 'Tròng kính Đa tròng Hoga 1.56 Progressive HC', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (160, 38, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/Hoga-1.56-Progressive-HC.jpg', 'Tròng kính Đa tròng Hoga 1.56 Progressive HC', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (161, 38, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/9-copy-1.jpg', 'Tròng kính Đa tròng Hoga 1.56 Progressive HC', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (162, 39, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/es-trans-gen-8-KHOI.jpg', 'Tròng Siêu Mỏng Lọc Ánh Sáng Xanh 1.60 U6 Chemi Hàn Quốc', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (163, 39, 'https://kinhmateyeplus.com/wp-content/uploads/2022/11/chemi-1.60-u6.png', 'Tròng Siêu Mỏng Lọc Ánh Sáng Xanh 1.60 U6 Chemi Hàn Quốc', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (164, 39, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/160-u6.png', 'Tròng Siêu Mỏng Lọc Ánh Sáng Xanh 1.60 U6 Chemi Hàn Quốc', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (165, 39, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/1-copy.jpg', 'Tròng Siêu Mỏng Lọc Ánh Sáng Xanh 1.60 U6 Chemi Hàn Quốc', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (166, 40, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/10-copy.jpg', 'TRÒNG KÍNH CHEMI X DRIVE 1.60 ASP', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (167, 40, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/17-copy.jpg', 'TRÒNG KÍNH CHEMI X DRIVE 1.60 ASP', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (168, 40, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/xdrive.jpg', 'TRÒNG KÍNH CHEMI X DRIVE 1.60 ASP', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (169, 40, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/3-copy.jpg', 'TRÒNG KÍNH CHEMI X DRIVE 1.60 ASP', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (170, 41, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/prevencia.png', 'Hộp 100 Khăn Nano Đa Năng', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (171, 41, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/khan-nano.jpg', 'Hộp 100 Khăn Nano Đa Năng', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (172, 41, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0010.jpg', 'Hộp 100 Khăn Nano Đa Năng', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (173, 41, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/14-copy.jpg', 'Hộp 100 Khăn Nano Đa Năng', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (174, 42, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/es-trans-gen-8-1.50.jpg', 'Kính Đổi Màu Chống Ánh Sáng Xanh Zinmy 1.60 AS Duramax', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (175, 42, 'https://kinhmateyeplus.com/wp-content/uploads/2022/11/Zinmy-doi-mau.png', 'Kính Đổi Màu Chống Ánh Sáng Xanh Zinmy 1.60 AS Duramax', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (176, 42, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/zinmy-160.png', 'Kính Đổi Màu Chống Ánh Sáng Xanh Zinmy 1.60 AS Duramax', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (177, 42, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/2-copy.jpg', 'Kính Đổi Màu Chống Ánh Sáng Xanh Zinmy 1.60 AS Duramax', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (178, 43, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/313499213_500279415477602_6065368677006869224_n.png', 'Tròng Siêu Mỏng Lọc Ánh Sáng Xanh 1.74 U6 Chemi Hàn Quốc', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (179, 43, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/es-trans-gen-8-1.50.jpg', 'Tròng Siêu Mỏng Lọc Ánh Sáng Xanh 1.74 U6 Chemi Hàn Quốc', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (180, 43, 'https://kinhmateyeplus.com/wp-content/uploads/2022/11/chemi-1.74-u6.png', 'Tròng Siêu Mỏng Lọc Ánh Sáng Xanh 1.74 U6 Chemi Hàn Quốc', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (181, 43, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/khan-nano.jpg', 'Tròng Siêu Mỏng Lọc Ánh Sáng Xanh 1.74 U6 Chemi Hàn Quốc', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (182, 43, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/9-copy-1.jpg', 'Tròng Siêu Mỏng Lọc Ánh Sáng Xanh 1.74 U6 Chemi Hàn Quốc', FALSE, 4, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (183, 44, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/element-1.61-1.png', 'Tròng Kính Đổi Màu Element 1.61 AS', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (184, 44, 'https://kinhmateyeplus.com/wp-content/uploads/2022/11/element-1.61.png', 'Tròng Kính Đổi Màu Element 1.61 AS', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (185, 44, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/2-trong.png', 'Tròng Kính Đổi Màu Element 1.61 AS', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (186, 44, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/7-copy.jpg', 'Tròng Kính Đổi Màu Element 1.61 AS', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (187, 45, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/Hoga-1.56-Progressive-blue-cut-free-form-SHMC-1.jpg', 'Tròng Kính Đa Tròng Hoga 1.56 Progressive Blue Cut Free Form SHMC', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (188, 45, 'https://kinhmateyeplus.com/wp-content/uploads/2024/10/Hoga-1.56-Progressive-blue-cut-free-form-SHMC-1.jpg', 'Tròng Kính Đa Tròng Hoga 1.56 Progressive Blue Cut Free Form SHMC', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (189, 45, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/313499213_500279415477602_6065368677006869224_n.png', 'Tròng Kính Đa Tròng Hoga 1.56 Progressive Blue Cut Free Form SHMC', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (190, 45, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/khan-nano.jpg', 'Tròng Kính Đa Tròng Hoga 1.56 Progressive Blue Cut Free Form SHMC', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (191, 46, 'https://kinhmateyeplus.com/wp-content/uploads/2024/10/Hoya-1.56-Progressive-Photo-Grey-SHMC-1-1024x1024.jpg', 'Tròng kính đa tròng Hoya 1.56 Progressive Photo Grey SHMC', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (192, 46, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/Hoya-1.56-Progressive-Photo-Grey-SHMC-1.jpg', 'Tròng kính đa tròng Hoya 1.56 Progressive Photo Grey SHMC', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (193, 46, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/prevencia.png', 'Tròng kính đa tròng Hoya 1.56 Progressive Photo Grey SHMC', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (194, 46, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/10-copy.jpg', 'Tròng kính đa tròng Hoya 1.56 Progressive Photo Grey SHMC', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (195, 47, 'https://kinhmateyeplus.com/wp-content/uploads/2023/01/hoga-sapphire.png', 'Kính Đổi Màu Chống Ánh Sáng Xanh HOGA Sapphire 1.56', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (196, 47, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/Hoga-saphire.png', 'Kính Đổi Màu Chống Ánh Sáng Xanh HOGA Sapphire 1.56', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (197, 47, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/khan-nano.jpg', 'Kính Đổi Màu Chống Ánh Sáng Xanh HOGA Sapphire 1.56', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (198, 47, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/2-trong.png', 'Kính Đổi Màu Chống Ánh Sáng Xanh HOGA Sapphire 1.56', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (199, 48, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/chemi-blue.png', 'TRÒNG KÍNH ĐỔI MÀU 3 IN 1 CHEMI 1.67 MR-7 ASP BLUE-BLOCK', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (200, 48, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/khan-nano.jpg', 'TRÒNG KÍNH ĐỔI MÀU 3 IN 1 CHEMI 1.67 MR-7 ASP BLUE-BLOCK', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (201, 48, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/hoga-blc.png', 'TRÒNG KÍNH ĐỔI MÀU 3 IN 1 CHEMI 1.67 MR-7 ASP BLUE-BLOCK', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (202, 48, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/7-copy.jpg', 'TRÒNG KÍNH ĐỔI MÀU 3 IN 1 CHEMI 1.67 MR-7 ASP BLUE-BLOCK', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (203, 49, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/Layer-3-copy-2.png', 'GỌNG KÍNH CẬN THỜI TRANG BLANCY DÁNG MẮT MÈO 0934 C1 ĐEN BÓNG', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (204, 49, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/Layer-13-2.png', 'GỌNG KÍNH CẬN THỜI TRANG BLANCY DÁNG MẮT MÈO 0934 C1 ĐEN BÓNG', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (205, 49, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/Layer-16.png', 'GỌNG KÍNH CẬN THỜI TRANG BLANCY DÁNG MẮT MÈO 0934 C1 ĐEN BÓNG', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (206, 49, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0869.png', 'GỌNG KÍNH CẬN THỜI TRANG BLANCY DÁNG MẮT MÈO 0934 C1 ĐEN BÓNG', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (207, 50, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0129-1.jpg', 'GỌNG KÍNH CẬN NHỰA CAO CẤP DÁNG MẮT MÈO EYEPLUS 8887 C26 TRONG', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (208, 50, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0128.jpg', 'GỌNG KÍNH CẬN NHỰA CAO CẤP DÁNG MẮT MÈO EYEPLUS 8887 C26 TRONG', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (209, 50, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0127.jpg', 'GỌNG KÍNH CẬN NHỰA CAO CẤP DÁNG MẮT MÈO EYEPLUS 8887 C26 TRONG', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (210, 51, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9718.jpg', 'GỌNG KÍNH EYEPLUS 8241', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (211, 51, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9722.jpg', 'GỌNG KÍNH EYEPLUS 8241', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (212, 51, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9651.jpg', 'GỌNG KÍNH EYEPLUS 8241', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (213, 51, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/den-bong.jpg', 'GỌNG KÍNH EYEPLUS 8241', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (214, 51, 'https://kinhmateyeplus.com/wp-content/uploads/2025/04/8241-c2.jpg', 'GỌNG KÍNH EYEPLUS 8241', FALSE, 4, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (215, 52, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0894-copy.png', 'GỌNG KÍNH CẬN THỜI TRANG BLANCY DÁNG MẮT MÈO 0934 C66 XÁM', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (216, 52, 'https://kinhmateyeplus.com/wp-content/uploads/2023/04/IMG_0894-copy.png', 'GỌNG KÍNH CẬN THỜI TRANG BLANCY DÁNG MẮT MÈO 0934 C66 XÁM', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (217, 52, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/Layer-3-copy.png', 'GỌNG KÍNH CẬN THỜI TRANG BLANCY DÁNG MẮT MÈO 0934 C66 XÁM', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (218, 52, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/Layer-2-copy.png', 'GỌNG KÍNH CẬN THỜI TRANG BLANCY DÁNG MẮT MÈO 0934 C66 XÁM', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (219, 53, 'https://kinhmateyeplus.com/wp-content/uploads/2025/04/98359-c2.jpg', 'GỌNG KÍNH REEMAN 98359', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (220, 53, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9208.jpg', 'GỌNG KÍNH REEMAN 98359', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (221, 53, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9718.jpg', 'GỌNG KÍNH REEMAN 98359', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (222, 53, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_8638.jpg', 'GỌNG KÍNH REEMAN 98359', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (223, 54, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/Layer-17-copy-3.png', 'GỌNG KÍNH TF 70119', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (224, 54, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9858.jpg', 'GỌNG KÍNH TF 70119', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (225, 54, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9976.jpg', 'GỌNG KÍNH TF 70119', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (226, 54, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0096.jpg', 'GỌNG KÍNH TF 70119', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (227, 54, 'https://kinhmateyeplus.com/wp-content/uploads/2025/04/70119.jpg', 'GỌNG KÍNH TF 70119', FALSE, 4, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (228, 55, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_1170.jpg', 'GỌNG KÍNH REEMAN 0455', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (229, 55, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_8638.jpg', 'GỌNG KÍNH REEMAN 0455', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (230, 55, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/DSCF1574.jpg', 'GỌNG KÍNH REEMAN 0455', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (231, 55, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9072.jpg', 'GỌNG KÍNH REEMAN 0455', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (232, 55, 'https://kinhmateyeplus.com/wp-content/uploads/2025/04/0455-c1.jpg', 'GỌNG KÍNH REEMAN 0455', FALSE, 4, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (233, 56, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/Layer-3-copy-2.png', 'GỌNG KÍNH REEMAN 3522', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (234, 56, 'https://kinhmateyeplus.com/wp-content/uploads/2025/05/IMG_8209.jpg', 'GỌNG KÍNH REEMAN 3522', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (235, 56, 'https://kinhmateyeplus.com/wp-content/uploads/2025/05/IMG_8243.jpg', 'GỌNG KÍNH REEMAN 3522', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (236, 56, 'https://kinhmateyeplus.com/wp-content/uploads/2025/05/IMG_8173.jpg', 'GỌNG KÍNH REEMAN 3522', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (237, 57, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9685.jpg', 'GỌNG KÍNH REEMAN 1175', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (238, 57, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_3309.jpg', 'GỌNG KÍNH REEMAN 1175', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (239, 57, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9208.jpg', 'GỌNG KÍNH REEMAN 1175', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (240, 57, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0125.jpg', 'GỌNG KÍNH REEMAN 1175', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (241, 57, 'https://kinhmateyeplus.com/wp-content/uploads/2025/04/1175-c4.jpg', 'GỌNG KÍNH REEMAN 1175', FALSE, 4, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (242, 58, 'https://kinhmateyeplus.com/wp-content/uploads/2025/04/30125-c3.jpg', 'GỌNG KÍNH EYEPLUS 30125', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (243, 58, 'https://kinhmateyeplus.com/wp-content/uploads/2025/04/30125-c4.jpg', 'GỌNG KÍNH EYEPLUS 30125', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (244, 58, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_3309.jpg', 'GỌNG KÍNH EYEPLUS 30125', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (245, 59, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_1997-1.jpg', 'GỌNG KÍNH REEMAN 0015 - GHI', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (246, 59, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_2029-1.jpg', 'GỌNG KÍNH REEMAN 0015 - GHI', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (247, 59, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_2052-1.jpg', 'GỌNG KÍNH REEMAN 0015 - GHI', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (248, 60, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/Layer-3-copy-2.png', 'GỌNG KÍNH REEMAN 0015 - BẠC', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (249, 60, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_2002-1.jpg', 'GỌNG KÍNH REEMAN 0015 - BẠC', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (250, 60, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_2031-1.jpg', 'GỌNG KÍNH REEMAN 0015 - BẠC', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (251, 60, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_2056-1.jpg', 'GỌNG KÍNH REEMAN 0015 - BẠC', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (252, 61, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/Layer-3-copy-2.png', 'GỌNG KÍNH EYEPLUS 00321', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (253, 61, 'https://kinhmateyeplus.com/wp-content/uploads/2025/04/00321-c5.jpg', 'GỌNG KÍNH EYEPLUS 00321', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (254, 61, 'https://kinhmateyeplus.com/wp-content/uploads/2025/04/00321-c6.jpg', 'GỌNG KÍNH EYEPLUS 00321', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (255, 61, 'https://kinhmateyeplus.com/wp-content/uploads/2025/04/00321-c2.jpg', 'GỌNG KÍNH EYEPLUS 00321', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (256, 62, 'https://kinhmateyeplus.com/wp-content/uploads/2025/04/080984-c3.jpg', 'GỌNG KÍNH REEMAN 80984', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (257, 62, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_3284.jpg', 'GỌNG KÍNH REEMAN 80984', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (258, 62, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9722.jpg', 'GỌNG KÍNH REEMAN 80984', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (259, 62, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9685.jpg', 'GỌNG KÍNH REEMAN 80984', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (260, 63, 'https://kinhmateyeplus.com/wp-content/uploads/2025/01/IMG_2338.jpg', 'GỌNG KÍNH REEMAN 2482', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (261, 63, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_3284.jpg', 'GỌNG KÍNH REEMAN 2482', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (262, 63, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_1170.jpg', 'GỌNG KÍNH REEMAN 2482', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (263, 63, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0376.jpg', 'GỌNG KÍNH REEMAN 2482', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (264, 64, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/Layer-3-copy-2.png', 'GỌNG KÍNH TF 70093', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (265, 64, 'https://kinhmateyeplus.com/wp-content/uploads/2025/04/70093-c2.jpg', 'GỌNG KÍNH TF 70093', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (266, 64, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0376.jpg', 'GỌNG KÍNH TF 70093', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (267, 64, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/DSCF1574.jpg', 'GỌNG KÍNH TF 70093', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (268, 65, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/271120241732727580.png', 'GỌNG KÍNH RÂM CẬN 2IN1 TJ21779 C1 ĐEN', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (269, 65, 'https://kinhmateyeplus.com/wp-content/uploads/2017/09/20250506-095454.jpg', 'GỌNG KÍNH RÂM CẬN 2IN1 TJ21779 C1 ĐEN', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (270, 65, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9907.png', 'GỌNG KÍNH RÂM CẬN 2IN1 TJ21779 C1 ĐEN', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (271, 65, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/39a.png', 'GỌNG KÍNH RÂM CẬN 2IN1 TJ21779 C1 ĐEN', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (272, 65, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/39b.png', 'GỌNG KÍNH RÂM CẬN 2IN1 TJ21779 C1 ĐEN', FALSE, 4, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (273, 66, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0655-2.jpg', 'GỌNG KÍNH RÂM CẬN 18070', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (274, 66, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0352.jpg', 'GỌNG KÍNH RÂM CẬN 18070', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (275, 66, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0563.jpg', 'GỌNG KÍNH RÂM CẬN 18070', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (276, 66, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0575.jpg', 'GỌNG KÍNH RÂM CẬN 18070', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (277, 67, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/271120241732727580.png', 'GỌNG KÍNH RÂM CẬN 2IN1 2211 C2 GỌNG NÂU MẮT NÂU', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (278, 67, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_6257.jpg', 'GỌNG KÍNH RÂM CẬN 2IN1 2211 C2 GỌNG NÂU MẮT NÂU', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (279, 67, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/10-copy.jpg', 'GỌNG KÍNH RÂM CẬN 2IN1 2211 C2 GỌNG NÂU MẮT NÂU', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (280, 67, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/36b.png', 'GỌNG KÍNH RÂM CẬN 2IN1 2211 C2 GỌNG NÂU MẮT NÂU', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (281, 67, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/36c.png', 'GỌNG KÍNH RÂM CẬN 2IN1 2211 C2 GỌNG NÂU MẮT NÂU', FALSE, 4, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (282, 68, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0964-1.jpg', 'GỌNG KÍNH RÂM CẬN 2IN1 KIM LOẠI 1007 C2 GỌNG VÀNG MẮT TÍM', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (283, 68, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9907.png', 'GỌNG KÍNH RÂM CẬN 2IN1 KIM LOẠI 1007 C2 GỌNG VÀNG MẮT TÍM', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (284, 68, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/41-2.jpg', 'GỌNG KÍNH RÂM CẬN 2IN1 KIM LOẠI 1007 C2 GỌNG VÀNG MẮT TÍM', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (285, 68, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/40-1.jpg', 'GỌNG KÍNH RÂM CẬN 2IN1 KIM LOẠI 1007 C2 GỌNG VÀNG MẮT TÍM', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (286, 68, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/42-2.jpg', 'GỌNG KÍNH RÂM CẬN 2IN1 KIM LOẠI 1007 C2 GỌNG VÀNG MẮT TÍM', FALSE, 4, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (287, 69, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/trong-element.jpg', 'GỌNG KÍNH RÂM CẬN 2IN1 18021 C3 ĐEN BÓNG', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (288, 69, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_1070-1.jpg', 'GỌNG KÍNH RÂM CẬN 2IN1 18021 C3 ĐEN BÓNG', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (289, 69, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0964-1.jpg', 'GỌNG KÍNH RÂM CẬN 2IN1 18021 C3 ĐEN BÓNG', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (290, 69, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0410.jpg', 'GỌNG KÍNH RÂM CẬN 2IN1 18021 C3 ĐEN BÓNG', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (291, 69, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0479.jpg', 'GỌNG KÍNH RÂM CẬN 2IN1 18021 C3 ĐEN BÓNG', FALSE, 4, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (292, 70, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/281120241732795699.jpeg', 'Gọng Kính Râm Cận Clubmaster 2in1 TJ8020', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (293, 70, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/8020-700x700-1.jpg', 'Gọng Kính Râm Cận Clubmaster 2in1 TJ8020', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (294, 70, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0377.jpg', 'Gọng Kính Râm Cận Clubmaster 2in1 TJ8020', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (295, 70, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9718.jpg', 'Gọng Kính Râm Cận Clubmaster 2in1 TJ8020', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (296, 71, 'https://kinhmateyeplus.com/wp-content/uploads/2019/09/TJ011-đồi-mồi-mắt-đen.jpg', 'Gọng Kính Râm Cận 2in1 TJ012', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (297, 71, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/281120241732795667.jpeg', 'Gọng Kính Râm Cận 2in1 TJ012', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (298, 71, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9722.jpg', 'Gọng Kính Râm Cận 2in1 TJ012', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (299, 71, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/den-bong.jpg', 'Gọng Kính Râm Cận 2in1 TJ012', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (300, 72, 'https://kinhmateyeplus.com/wp-content/uploads/2017/09/20250506-095454.jpg', 'GỌNG KÍNH RÂM CẬN 2IN1 22440 C1 GỌNG ĐEN MẮT ĐEN', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (301, 72, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9944.png', 'GỌNG KÍNH RÂM CẬN 2IN1 22440 C1 GỌNG ĐEN MẮT ĐEN', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (302, 72, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/1-1-1.png', 'GỌNG KÍNH RÂM CẬN 2IN1 22440 C1 GỌNG ĐEN MẮT ĐEN', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (303, 72, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/33-1.png', 'GỌNG KÍNH RÂM CẬN 2IN1 22440 C1 GỌNG ĐEN MẮT ĐEN', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (304, 73, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_1070-1.jpg', 'GỌNG KÍNH RÂM CẬN 2IN1 90114 C2 ĐEN', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (305, 73, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9907.png', 'GỌNG KÍNH RÂM CẬN 2IN1 90114 C2 ĐEN', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (306, 73, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/17-copy.jpg', 'GỌNG KÍNH RÂM CẬN 2IN1 90114 C2 ĐEN', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (307, 73, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/1-1-1.png', 'GỌNG KÍNH RÂM CẬN 2IN1 90114 C2 ĐEN', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (308, 73, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/32a.png', 'GỌNG KÍNH RÂM CẬN 2IN1 90114 C2 ĐEN', FALSE, 4, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (309, 74, 'https://kinhmateyeplus.com/wp-content/uploads/2019/09/8023-700x700-1.jpg', 'Gọng Kính Râm Cận 2in1 TJ8023', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (310, 74, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_3284.jpg', 'Gọng Kính Râm Cận 2in1 TJ8023', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (311, 74, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0687.jpg', 'Gọng Kính Râm Cận 2in1 TJ8023', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (312, 74, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9208.jpg', 'Gọng Kính Râm Cận 2in1 TJ8023', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (313, 75, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0964-1.jpg', 'GỌNG KÍNH RÂM CẬN 2IN1 222005 C4 XÁM', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (314, 75, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/31-1.png', 'GỌNG KÍNH RÂM CẬN 2IN1 222005 C4 XÁM', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (315, 75, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/31b.png', 'GỌNG KÍNH RÂM CẬN 2IN1 222005 C4 XÁM', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (316, 75, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/31c.png', 'GỌNG KÍNH RÂM CẬN 2IN1 222005 C4 XÁM', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (317, 76, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/es-trans-gen-8-1.50.jpg', 'GỌNG KÍNH RÂM CẬN 2IN1 TJ21779 C1 ĐEN', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (318, 76, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/271120241732727580.png', 'GỌNG KÍNH RÂM CẬN 2IN1 TJ21779 C1 ĐEN', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (319, 76, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/271120241732727582.png', 'GỌNG KÍNH RÂM CẬN 2IN1 TJ21779 C1 ĐEN', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (320, 76, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/271120241732727584.png', 'GỌNG KÍNH RÂM CẬN 2IN1 TJ21779 C1 ĐEN', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (321, 76, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/271120241732727587.png', 'GỌNG KÍNH RÂM CẬN 2IN1 TJ21779 C1 ĐEN', FALSE, 4, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (322, 77, 'https://kinhmateyeplus.com/wp-content/uploads/2019/08/011-càng-xanh-mắt-đen-web-768x768-1.jpg', 'Gọng Kính Râm Cận 2in1 TJ011', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (323, 77, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/281120241732795688.jpeg', 'Gọng Kính Râm Cận 2in1 TJ011', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (324, 77, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0376.jpg', 'Gọng Kính Râm Cận 2in1 TJ011', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (325, 78, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/DSCF6937-copy-768x512-1.jpg', 'Gọng Kính Râm Cận 2in1 TJ8036', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (326, 78, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/281120241732795899.jpeg', 'Gọng Kính Râm Cận 2in1 TJ8036', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (327, 78, 'https://kinhmateyeplus.com/wp-content/uploads/2019/07/DSCF6937-copy.jpg', 'Gọng Kính Râm Cận 2in1 TJ8036', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (328, 78, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0125.jpg', 'Gọng Kính Râm Cận 2in1 TJ8036', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (329, 79, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/es-trans-gen-8-1.50.jpg', 'GỌNG KÍNH RÂM CẬN 2IN1 22440 C4 GỌNG NÂU MẮT CAM', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (330, 79, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/271120241732727580.png', 'GỌNG KÍNH RÂM CẬN 2IN1 22440 C4 GỌNG NÂU MẮT CAM', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (331, 79, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/trong-element.jpg', 'GỌNG KÍNH RÂM CẬN 2IN1 22440 C4 GỌNG NÂU MẮT CAM', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (332, 79, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9909.png', 'GỌNG KÍNH RÂM CẬN 2IN1 22440 C4 GỌNG NÂU MẮT CAM', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (333, 79, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/29b.png', 'GỌNG KÍNH RÂM CẬN 2IN1 22440 C4 GỌNG NÂU MẮT CAM', FALSE, 4, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (334, 80, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/es-trans-gen-8-KHOI.jpg', 'GỌNG KÍNH RÂM CẬN 2IN1 222002 C2 ĐEN NHÁM', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (335, 80, 'https://kinhmateyeplus.com/wp-content/uploads/2017/09/20250506-095454.jpg', 'GỌNG KÍNH RÂM CẬN 2IN1 222002 C2 ĐEN NHÁM', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (336, 80, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/33-1.png', 'GỌNG KÍNH RÂM CẬN 2IN1 222002 C2 ĐEN NHÁM', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (337, 80, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/38b.png', 'GỌNG KÍNH RÂM CẬN 2IN1 222002 C2 ĐEN NHÁM', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (338, 80, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/38c.png', 'GỌNG KÍNH RÂM CẬN 2IN1 222002 C2 ĐEN NHÁM', FALSE, 4, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (339, 81, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/es-trans-gen-8-KHOI.jpg', 'GỌNG KÍNH RÂM CẬN 2IN1 22440 C3 GỌNG XÁM MẮT HỒNG', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (340, 81, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0964-1.jpg', 'GỌNG KÍNH RÂM CẬN 2IN1 22440 C3 GỌNG XÁM MẮT HỒNG', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (341, 81, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9909.png', 'GỌNG KÍNH RÂM CẬN 2IN1 22440 C3 GỌNG XÁM MẮT HỒNG', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (342, 81, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/5.4-.png', 'GỌNG KÍNH RÂM CẬN 2IN1 22440 C3 GỌNG XÁM MẮT HỒNG', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (343, 81, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/34b.png', 'GỌNG KÍNH RÂM CẬN 2IN1 22440 C3 GỌNG XÁM MẮT HỒNG', FALSE, 4, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (344, 82, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/Layer-3-copy-2.png', 'GỌNG KÍNH 68165 – GỌNG KHOAN ĐÍNH CHARM', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (345, 82, 'https://kinhmateyeplus.com/wp-content/uploads/2025/08/IMG_1793-1.jpg', 'GỌNG KÍNH 68165 – GỌNG KHOAN ĐÍNH CHARM', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (346, 82, 'https://kinhmateyeplus.com/wp-content/uploads/2025/08/IMG_1793.jpg', 'GỌNG KÍNH 68165 – GỌNG KHOAN ĐÍNH CHARM', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (347, 82, 'https://kinhmateyeplus.com/wp-content/uploads/2025/08/IMG_1740.jpg', 'GỌNG KÍNH 68165 – GỌNG KHOAN ĐÍNH CHARM', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (348, 83, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/Layer-3-copy-2.png', 'GỌNG KÍNH REEMAN ACETATE 120 C1 ĐEN', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (349, 83, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0211-1.jpg', 'GỌNG KÍNH REEMAN ACETATE 120 C1 ĐEN', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (350, 83, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0241.jpg', 'GỌNG KÍNH REEMAN ACETATE 120 C1 ĐEN', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (351, 83, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0303.jpg', 'GỌNG KÍNH REEMAN ACETATE 120 C1 ĐEN', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (352, 84, 'https://kinhmateyeplus.com/wp-content/uploads/2024/10/IMG_9122-1.png', 'GỌNG KÍNH REEMAN ACETATE 643 C81 ĐỒI MỒI', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (353, 84, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9086.png', 'GỌNG KÍNH REEMAN ACETATE 643 C81 ĐỒI MỒI', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (354, 84, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9105.jpg', 'GỌNG KÍNH REEMAN ACETATE 643 C81 ĐỒI MỒI', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (355, 84, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9122.png', 'GỌNG KÍNH REEMAN ACETATE 643 C81 ĐỒI MỒI', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (356, 85, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/DSC01680.jpg', 'GỌNG KÍNH CẬN BLANCY 6779 C7 ĐEN BÓNG VIỀN BẠC', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (357, 85, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/DSC01774.jpg', 'GỌNG KÍNH CẬN BLANCY 6779 C7 ĐEN BÓNG VIỀN BẠC', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (358, 85, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/DSC01818.jpg', 'GỌNG KÍNH CẬN BLANCY 6779 C7 ĐEN BÓNG VIỀN BẠC', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (359, 85, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/DSC01860.jpg', 'GỌNG KÍNH CẬN BLANCY 6779 C7 ĐEN BÓNG VIỀN BẠC', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (360, 85, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/DSC01726.jpg', 'GỌNG KÍNH CẬN BLANCY 6779 C7 ĐEN BÓNG VIỀN BẠC', FALSE, 4, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (361, 86, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_2153.jpg', 'GỌNG KÍNH CẬN EYEPLUS 2505 C6 XÁM', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (362, 86, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_2130.jpg', 'GỌNG KÍNH CẬN EYEPLUS 2505 C6 XÁM', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (363, 86, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_2140.jpg', 'GỌNG KÍNH CẬN EYEPLUS 2505 C6 XÁM', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (364, 86, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_2163.jpg', 'GỌNG KÍNH CẬN EYEPLUS 2505 C6 XÁM', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (365, 87, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/15115-c53.jpg', 'GỌNG KÍNH CẬN BLANCY 15115 C53 ĐEN BẠC', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (366, 87, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_2122.jpg', 'GỌNG KÍNH CẬN BLANCY 15115 C53 ĐEN BẠC', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (367, 87, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_2133.jpg', 'GỌNG KÍNH CẬN BLANCY 15115 C53 ĐEN BẠC', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (368, 87, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_2156.jpg', 'GỌNG KÍNH CẬN BLANCY 15115 C53 ĐEN BẠC', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (369, 88, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/Layer-3-copy-2.png', 'GỌNG KÍNH CẬN EYEPLUS 2505 C2 ĐEN NHÁM', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (370, 88, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0863-1.jpg', 'GỌNG KÍNH CẬN EYEPLUS 2505 C2 ĐEN NHÁM', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (371, 88, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0875-2.jpg', 'GỌNG KÍNH CẬN EYEPLUS 2505 C2 ĐEN NHÁM', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (372, 88, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0886-2.jpg', 'GỌNG KÍNH CẬN EYEPLUS 2505 C2 ĐEN NHÁM', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (373, 88, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0897-2.jpg', 'GỌNG KÍNH CẬN EYEPLUS 2505 C2 ĐEN NHÁM', FALSE, 4, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (374, 89, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/Layer-3-copy-2.png', 'GỌNG KÍNH REEMAN ACETATE 524 C25 ĐỒI MỒI', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (375, 89, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9810-1.jpg', 'GỌNG KÍNH REEMAN ACETATE 524 C25 ĐỒI MỒI', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (376, 89, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9831.png', 'GỌNG KÍNH REEMAN ACETATE 524 C25 ĐỒI MỒI', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (377, 89, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9851.jpg', 'GỌNG KÍNH REEMAN ACETATE 524 C25 ĐỒI MỒI', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (378, 90, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/DSC01664.jpg', 'GỌNG KÍNH CẬN BLANCY 7184 C2 ĐEN BÓNG VIỀN BẠC', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (379, 90, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/DSC01764.jpg', 'GỌNG KÍNH CẬN BLANCY 7184 C2 ĐEN BÓNG VIỀN BẠC', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (380, 90, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/DSC01811.jpg', 'GỌNG KÍNH CẬN BLANCY 7184 C2 ĐEN BÓNG VIỀN BẠC', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (381, 90, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/DSC01851.jpg', 'GỌNG KÍNH CẬN BLANCY 7184 C2 ĐEN BÓNG VIỀN BẠC', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (382, 90, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/DSC01717.jpg', 'GỌNG KÍNH CẬN BLANCY 7184 C2 ĐEN BÓNG VIỀN BẠC', FALSE, 4, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (383, 91, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/DSC01703.jpg', 'GỌNG KÍNH CẬN BLANCY CLUB MASTER 14250 C11 ĐỒI MỒI', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (384, 91, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/DSC01793.jpg', 'GỌNG KÍNH CẬN BLANCY CLUB MASTER 14250 C11 ĐỒI MỒI', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (385, 91, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/DSC01838.jpg', 'GỌNG KÍNH CẬN BLANCY CLUB MASTER 14250 C11 ĐỒI MỒI', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (386, 91, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/DSC01881.jpg', 'GỌNG KÍNH CẬN BLANCY CLUB MASTER 14250 C11 ĐỒI MỒI', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (387, 92, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/DSC01774-2.jpg', 'GỌNG KÍNH CẬN BLANCY 6779 C8 ĐEN BÓNG VIỀN GHI', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (388, 92, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/DSC01681.jpg', 'GỌNG KÍNH CẬN BLANCY 6779 C8 ĐEN BÓNG VIỀN GHI', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (389, 92, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/DSC01819.jpg', 'GỌNG KÍNH CẬN BLANCY 6779 C8 ĐEN BÓNG VIỀN GHI', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (390, 92, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/DSC01861.jpg', 'GỌNG KÍNH CẬN BLANCY 6779 C8 ĐEN BÓNG VIỀN GHI', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (391, 93, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/Layer-3-copy-2.png', 'GỌNG KÍNH REEMAN ACETATE 524 C12 RÊU', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (392, 93, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9807-1.jpg', 'GỌNG KÍNH REEMAN ACETATE 524 C12 RÊU', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (393, 93, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9849-1.jpg', 'GỌNG KÍNH REEMAN ACETATE 524 C12 RÊU', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (394, 94, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/Untitled-1-copy-1-1.png', 'GỌNG KÍNH CẬN BLANCY 20226 C2 ĐEN VIỀN BẠC', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (395, 94, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_7422.png', 'GỌNG KÍNH CẬN BLANCY 20226 C2 ĐEN VIỀN BẠC', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (396, 94, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_7491.png', 'GỌNG KÍNH CẬN BLANCY 20226 C2 ĐEN VIỀN BẠC', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (397, 95, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9208.jpg', 'GỌNG KÍNH CẬN BLANCY DÁNG CLUB MASTER 8902 C1/1 ĐEN BẠC', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (398, 95, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_7929.jpg', 'GỌNG KÍNH CẬN BLANCY DÁNG CLUB MASTER 8902 C1/1 ĐEN BẠC', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (399, 95, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9685.jpg', 'GỌNG KÍNH CẬN BLANCY DÁNG CLUB MASTER 8902 C1/1 ĐEN BẠC', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (400, 95, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9722.jpg', 'GỌNG KÍNH CẬN BLANCY DÁNG CLUB MASTER 8902 C1/1 ĐEN BẠC', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (401, 95, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/8902-2.png', 'GỌNG KÍNH CẬN BLANCY DÁNG CLUB MASTER 8902 C1/1 ĐEN BẠC', FALSE, 4, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (402, 96, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/z4854135865718_13e584cd651c884caf1ac755158f8687.jpg', 'GỌNG KÍNH REEMAN 32048 C1 ĐEN VIỀN BẠC', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (403, 96, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_5982.jpg', 'GỌNG KÍNH REEMAN 32048 C1 ĐEN VIỀN BẠC', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (404, 96, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_5984.jpg', 'GỌNG KÍNH REEMAN 32048 C1 ĐEN VIỀN BẠC', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (405, 96, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_5985.jpg', 'GỌNG KÍNH REEMAN 32048 C1 ĐEN VIỀN BẠC', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (406, 97, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_8280.jpg', 'GỌNG KÍNH EYEPLUS 7226', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (407, 97, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_8260.jpg', 'GỌNG KÍNH EYEPLUS 7226', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (408, 97, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_8269.jpg', 'GỌNG KÍNH EYEPLUS 7226', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (409, 97, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9299.jpg', 'GỌNG KÍNH EYEPLUS 7226', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (410, 97, 'https://kinhmateyeplus.com/wp-content/uploads/2025/04/7226-c3.jpg', 'GỌNG KÍNH EYEPLUS 7226', FALSE, 4, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (411, 98, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_1066.jpg', 'GỌNG KÍNH CẬN MAYBLANC 1013 C1 ĐEN ĐỎ', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (412, 98, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_1065.jpg', 'GỌNG KÍNH CẬN MAYBLANC 1013 C1 ĐEN ĐỎ', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (413, 98, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_1067.jpg', 'GỌNG KÍNH CẬN MAYBLANC 1013 C1 ĐEN ĐỎ', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (414, 98, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_1068.jpg', 'GỌNG KÍNH CẬN MAYBLANC 1013 C1 ĐEN ĐỎ', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (415, 99, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_5847.jpg', 'GỌNG KÍNH CẬN EYE PLUS CLUB MASTER 5102 C3 GHI GỌNG ĐEN', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (416, 99, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_5846.jpg', 'GỌNG KÍNH CẬN EYE PLUS CLUB MASTER 5102 C3 GHI GỌNG ĐEN', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (417, 99, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_5848.jpg', 'GỌNG KÍNH CẬN EYE PLUS CLUB MASTER 5102 C3 GHI GỌNG ĐEN', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (418, 99, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_5851.jpg', 'GỌNG KÍNH CẬN EYE PLUS CLUB MASTER 5102 C3 GHI GỌNG ĐEN', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (419, 100, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_5840.jpg', 'GỌNG KÍNH REEMAN 3518 C1 ĐEN VIỀN GHI', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (420, 100, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_5842.jpg', 'GỌNG KÍNH REEMAN 3518 C1 ĐEN VIỀN GHI', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (421, 100, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_5844.jpg', 'GỌNG KÍNH REEMAN 3518 C1 ĐEN VIỀN GHI', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (422, 100, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_5845.jpg', 'GỌNG KÍNH REEMAN 3518 C1 ĐEN VIỀN GHI', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (423, 100, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_5843.jpg', 'GỌNG KÍNH REEMAN 3518 C1 ĐEN VIỀN GHI', FALSE, 4, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (424, 101, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_2798.jpg', 'GỌNG KÍNH CẬN BLANCY TITANIUM 89060 C355 ĐEN VIỀN GHI', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (425, 101, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_2799.jpg', 'GỌNG KÍNH CẬN BLANCY TITANIUM 89060 C355 ĐEN VIỀN GHI', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (426, 101, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_2800.jpg', 'GỌNG KÍNH CẬN BLANCY TITANIUM 89060 C355 ĐEN VIỀN GHI', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (427, 101, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_2801.jpg', 'GỌNG KÍNH CẬN BLANCY TITANIUM 89060 C355 ĐEN VIỀN GHI', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (428, 102, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/Layer-1-copy-3-8.jpg', 'GỌNG KÍNH CẬN BLANCY 17762 C2 ĐEN VIỀN BẠC', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (429, 102, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/Layer-1-copy-5-7.jpg', 'GỌNG KÍNH CẬN BLANCY 17762 C2 ĐEN VIỀN BẠC', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (430, 102, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/Layer-1-copy-4-7.jpg', 'GỌNG KÍNH CẬN BLANCY 17762 C2 ĐEN VIỀN BẠC', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (431, 103, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_7448.png', 'GỌNG KÍNH CẬN BLANCY 70772 C2 ĐEN BÓNG', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (432, 103, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_7412.jpg', 'GỌNG KÍNH CẬN BLANCY 70772 C2 ĐEN BÓNG', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (433, 103, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_7482.png', 'GỌNG KÍNH CẬN BLANCY 70772 C2 ĐEN BÓNG', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (434, 104, 'https://kinhmateyeplus.com/wp-content/uploads/2025/04/70093-c2.jpg', 'GỌNG KÍNH TF 70093', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (435, 104, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/DSCF1574.jpg', 'GỌNG KÍNH TF 70093', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (436, 104, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0125.jpg', 'GỌNG KÍNH TF 70093', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (437, 104, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9685.jpg', 'GỌNG KÍNH TF 70093', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (438, 105, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/Untitled-6-copy.jpg', 'GỌNG KÍNH CẬN BLANCY TITANIUM 89072 C455 ĐEN BẠC', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (439, 105, 'https://kinhmateyeplus.com/wp-content/uploads/2023/10/Untitled-6-copy.jpg', 'GỌNG KÍNH CẬN BLANCY TITANIUM 89072 C455 ĐEN BẠC', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (440, 105, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_2814.jpg', 'GỌNG KÍNH CẬN BLANCY TITANIUM 89072 C455 ĐEN BẠC', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (441, 105, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_2812.jpg', 'GỌNG KÍNH CẬN BLANCY TITANIUM 89072 C455 ĐEN BẠC', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (442, 106, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/es-trans-gen-8-KHOI.jpg', 'Tròng Kính Đổi Màu Vision X 1.60 AS SUN PROTECT CHỐNG ÁNH SÁNG XANH', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (443, 106, 'https://kinhmateyeplus.com/wp-content/uploads/2022/08/vision-web.png', 'Tròng Kính Đổi Màu Vision X 1.60 AS SUN PROTECT CHỐNG ÁNH SÁNG XANH', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (444, 106, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/prevencia.png', 'Tròng Kính Đổi Màu Vision X 1.60 AS SUN PROTECT CHỐNG ÁNH SÁNG XANH', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (445, 106, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/vision.png', 'Tròng Kính Đổi Màu Vision X 1.60 AS SUN PROTECT CHỐNG ÁNH SÁNG XANH', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (446, 107, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/es-trans-gen-8-1.50.jpg', 'Tròng Siêu Mỏng Lọc Ánh Sáng Xanh 1.60 U6 Chemi Hàn Quốc', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (447, 107, 'https://kinhmateyeplus.com/wp-content/uploads/2017/09/20250506-095454.jpg', 'Tròng Siêu Mỏng Lọc Ánh Sáng Xanh 1.60 U6 Chemi Hàn Quốc', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (448, 107, 'https://kinhmateyeplus.com/wp-content/uploads/2022/11/chemi-1.60-u6.png', 'Tròng Siêu Mỏng Lọc Ánh Sáng Xanh 1.60 U6 Chemi Hàn Quốc', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (449, 107, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/khan-nano.jpg', 'Tròng Siêu Mỏng Lọc Ánh Sáng Xanh 1.60 U6 Chemi Hàn Quốc', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (450, 108, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/es-trans-gen-8-1.50.jpg', 'Tròng Đổi Màu Essilor Transition Gen 8 STYLE COLORS 1.60 AS', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (451, 108, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0804-1.jpg', 'Tròng Đổi Màu Essilor Transition Gen 8 STYLE COLORS 1.60 AS', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (452, 108, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0801-1.jpg', 'Tròng Đổi Màu Essilor Transition Gen 8 STYLE COLORS 1.60 AS', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (453, 108, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0850-1.jpg', 'Tròng Đổi Màu Essilor Transition Gen 8 STYLE COLORS 1.60 AS', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (454, 109, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/es-trans-gen-8-1.50.jpg', 'Tròng Đổi Màu Essilor Transition Gen 8 STYLE COLORS 1.50 SPH STYLE COLORS', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (455, 109, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/adadasdasd.jpg', 'Tròng Đổi Màu Essilor Transition Gen 8 STYLE COLORS 1.50 SPH STYLE COLORS', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (456, 109, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0825-1.jpg', 'Tròng Đổi Màu Essilor Transition Gen 8 STYLE COLORS 1.50 SPH STYLE COLORS', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (457, 109, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0804-1.jpg', 'Tròng Đổi Màu Essilor Transition Gen 8 STYLE COLORS 1.50 SPH STYLE COLORS', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (458, 110, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/313499213_500279415477602_6065368677006869224_n.png', 'Kính Đổi Màu Chống Ánh Sáng Xanh Zinmy 1.60 AS Duramax', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (459, 110, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/es-trans-gen-8-KHOI.jpg', 'Kính Đổi Màu Chống Ánh Sáng Xanh Zinmy 1.60 AS Duramax', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (460, 110, 'https://kinhmateyeplus.com/wp-content/uploads/2022/11/Zinmy-doi-mau.png', 'Kính Đổi Màu Chống Ánh Sáng Xanh Zinmy 1.60 AS Duramax', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (461, 110, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/zinmy-160.png', 'Kính Đổi Màu Chống Ánh Sáng Xanh Zinmy 1.60 AS Duramax', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (462, 111, 'https://kinhmateyeplus.com/wp-content/uploads/2022/06/HOGA-1.56-HMC-2-TRONG.jpg', 'Tròng kính 1.56 - HOGA 1.56 UV400 SHMC- PLUS', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (463, 111, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/es-trans-gen-8-KHOI.jpg', 'Tròng kính 1.56 - HOGA 1.56 UV400 SHMC- PLUS', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (464, 111, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/2-trong.png', 'Tròng kính 1.56 - HOGA 1.56 UV400 SHMC- PLUS', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (465, 111, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/14-copy.jpg', 'Tròng kính 1.56 - HOGA 1.56 UV400 SHMC- PLUS', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (466, 112, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/es-trans-gen-8-KHOI.jpg', 'Tròng Siêu Mỏng 1.67 U6 Chemi Hàn Quốc', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (467, 112, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/chemi-1.67.jpg', 'Tròng Siêu Mỏng 1.67 U6 Chemi Hàn Quốc', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (468, 112, 'https://kinhmateyeplus.com/wp-content/uploads/2022/03/chemi-1.67.jpg', 'Tròng Siêu Mỏng 1.67 U6 Chemi Hàn Quốc', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (469, 112, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/khan-nano.jpg', 'Tròng Siêu Mỏng 1.67 U6 Chemi Hàn Quốc', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (470, 113, 'https://kinhmateyeplus.com/wp-content/uploads/2017/09/20250506-095454.jpg', 'Tròng Siêu Mỏng Lọc Ánh Sáng Xanh 1.74 U6 Chemi Hàn Quốc', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (471, 113, 'https://kinhmateyeplus.com/wp-content/uploads/2022/11/chemi-1.74-u6.png', 'Tròng Siêu Mỏng Lọc Ánh Sáng Xanh 1.74 U6 Chemi Hàn Quốc', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (472, 113, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/chemi-1.67.jpg', 'Tròng Siêu Mỏng Lọc Ánh Sáng Xanh 1.74 U6 Chemi Hàn Quốc', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (473, 113, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/prevencia.png', 'Tròng Siêu Mỏng Lọc Ánh Sáng Xanh 1.74 U6 Chemi Hàn Quốc', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (474, 113, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/174-u6.png', 'Tròng Siêu Mỏng Lọc Ánh Sáng Xanh 1.74 U6 Chemi Hàn Quốc', FALSE, 4, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (475, 114, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/es-trans-gen-8-KHOI.jpg', 'Tròng Kính Đổi Màu Element 1.61 AS', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (476, 114, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/element-1.61-1.png', 'Tròng Kính Đổi Màu Element 1.61 AS', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (477, 114, 'https://kinhmateyeplus.com/wp-content/uploads/2022/11/element-1.61.png', 'Tròng Kính Đổi Màu Element 1.61 AS', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (478, 114, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/prevencia.png', 'Tròng Kính Đổi Màu Element 1.61 AS', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (479, 115, 'https://kinhmateyeplus.com/wp-content/uploads/2022/03/ess-crizal-pre-1.png', 'Tròng Siêu Mỏng Essilor Crizal Prevencia chiết suất 1.67', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (480, 115, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/trong-element.jpg', 'Tròng Siêu Mỏng Essilor Crizal Prevencia chiết suất 1.67', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (481, 115, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/prevencia.png', 'Tròng Siêu Mỏng Essilor Crizal Prevencia chiết suất 1.67', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (482, 115, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/hoga-blc.png', 'Tròng Siêu Mỏng Essilor Crizal Prevencia chiết suất 1.67', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (483, 116, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/313499213_500279415477602_6065368677006869224_n.png', 'Tròng Siêu Mỏng 1.67 HPV Hoya Nhật Bản', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (484, 116, 'https://kinhmateyeplus.com/wp-content/uploads/2022/03/313499213_500279415477602_6065368677006869224_n.png', 'Tròng Siêu Mỏng 1.67 HPV Hoya Nhật Bản', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (485, 116, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/trong-element.jpg', 'Tròng Siêu Mỏng 1.67 HPV Hoya Nhật Bản', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (486, 116, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/khan-nano.jpg', 'Tròng Siêu Mỏng 1.67 HPV Hoya Nhật Bản', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (487, 117, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/es-trans-gen-8-1.50.jpg', 'TRÒNG KÍNH ĐỔI MÀU 3 IN 1 CHEMI 1.67 MR-7 ASP BLUE-BLOCK', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (488, 117, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/chemi-blue.png', 'TRÒNG KÍNH ĐỔI MÀU 3 IN 1 CHEMI 1.67 MR-7 ASP BLUE-BLOCK', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (489, 117, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/prevencia.png', 'TRÒNG KÍNH ĐỔI MÀU 3 IN 1 CHEMI 1.67 MR-7 ASP BLUE-BLOCK', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (490, 117, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/17-copy.jpg', 'TRÒNG KÍNH ĐỔI MÀU 3 IN 1 CHEMI 1.67 MR-7 ASP BLUE-BLOCK', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (491, 118, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/es-trans-gen-8-1.50.jpg', 'Tròng Đổi Màu Essilor Classic 1.56', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (492, 118, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/es-trans-gen-8-KHOI.jpg', 'Tròng Đổi Màu Essilor Classic 1.56', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (493, 118, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9901-1.png', 'Tròng Đổi Màu Essilor Classic 1.56', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (494, 118, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0850-1.jpg', 'Tròng Đổi Màu Essilor Classic 1.56', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (495, 118, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/10-copy.jpg', 'Tròng Đổi Màu Essilor Classic 1.56', FALSE, 4, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (496, 119, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/313499213_500279415477602_6065368677006869224_n.png', 'Tròng Kính Đổi Màu Vision X 1.60 AS SUN PROTECT CHỐNG ÁNH SÁNG XANH', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (497, 119, 'https://kinhmateyeplus.com/wp-content/uploads/2022/08/vision-web.png', 'Tròng Kính Đổi Màu Vision X 1.60 AS SUN PROTECT CHỐNG ÁNH SÁNG XANH', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (498, 119, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/prevencia.png', 'Tròng Kính Đổi Màu Vision X 1.60 AS SUN PROTECT CHỐNG ÁNH SÁNG XANH', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (499, 119, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/17-copy.jpg', 'Tròng Kính Đổi Màu Vision X 1.60 AS SUN PROTECT CHỐNG ÁNH SÁNG XANH', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (500, 119, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/2-trong.png', 'Tròng Kính Đổi Màu Vision X 1.60 AS SUN PROTECT CHỐNG ÁNH SÁNG XANH', FALSE, 4, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (501, 120, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/es-trans-gen-8-1.50.jpg', 'Tròng Đổi Màu Essilor Transition Gen 8 STYLE COLORS 1.60 AS', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (502, 120, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/es-trans-gen-8-KHOI.jpg', 'Tròng Đổi Màu Essilor Transition Gen 8 STYLE COLORS 1.60 AS', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (503, 120, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0804-1.jpg', 'Tròng Đổi Màu Essilor Transition Gen 8 STYLE COLORS 1.60 AS', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (504, 121, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/es-trans-gen-8-1.50.jpg', 'Tròng Kính Đổi Màu Element 1.67', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (505, 121, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/trong-element.jpg', 'Tròng Kính Đổi Màu Element 1.67', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (506, 121, 'https://kinhmateyeplus.com/wp-content/uploads/2022/03/trong-element.jpg', 'Tròng Kính Đổi Màu Element 1.67', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (507, 121, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0827-1.jpg', 'Tròng Kính Đổi Màu Element 1.67', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (508, 122, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/67732841_2299681390152632_665670671900082176_n-e1564393231594.jpg', 'Tròng Kính Chemi Crystal U2 Coated 1.60', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (509, 122, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/es-trans-gen-8-KHOI.jpg', 'Tròng Kính Chemi Crystal U2 Coated 1.60', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (510, 122, 'https://kinhmateyeplus.com/wp-content/uploads/2017/09/20250506-095454.jpg', 'Tròng Kính Chemi Crystal U2 Coated 1.60', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (511, 122, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/15-copy.jpg', 'Tròng Kính Chemi Crystal U2 Coated 1.60', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (512, 122, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/17-copy.jpg', 'Tròng Kính Chemi Crystal U2 Coated 1.60', FALSE, 4, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (513, 123, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/es-trans-gen-8-1.50.jpg', 'Tròng Đổi Màu Essilor Transition Gen 8 STYLE COLORS 1.50 SPH STYLE COLORS', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (514, 123, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/trong-element.jpg', 'Tròng Đổi Màu Essilor Transition Gen 8 STYLE COLORS 1.50 SPH STYLE COLORS', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (515, 123, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0777-1.jpg', 'Tròng Đổi Màu Essilor Transition Gen 8 STYLE COLORS 1.50 SPH STYLE COLORS', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (516, 123, 'https://kinhmateyeplus.com/wp-content/uploads/2022/03/bang-mau.png', 'Tròng Đổi Màu Essilor Transition Gen 8 STYLE COLORS 1.50 SPH STYLE COLORS', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (517, 124, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/es-trans-gen-8-KHOI.jpg', 'Tròng Đổi Màu Essilor Transition Gen 8 1.59 AS Airwear Màu Khói', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (518, 124, 'https://kinhmateyeplus.com/wp-content/uploads/2023/06/Group-4-copy-3.jpg', 'Tròng Đổi Màu Essilor Transition Gen 8 1.59 AS Airwear Màu Khói', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (519, 124, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0866-1.jpg', 'Tròng Đổi Màu Essilor Transition Gen 8 1.59 AS Airwear Màu Khói', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (520, 124, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0829-1.jpg', 'Tròng Đổi Màu Essilor Transition Gen 8 1.59 AS Airwear Màu Khói', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (521, 125, 'https://kinhmateyeplus.com/wp-content/uploads/2022/11/Zinmy-doi-mau.png', 'Kính Đổi Màu Chống Ánh Sáng Xanh Zinmy 1.60 AS Duramax', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (522, 125, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/zinmy-160.png', 'Kính Đổi Màu Chống Ánh Sáng Xanh Zinmy 1.60 AS Duramax', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (523, 125, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/14-copy.jpg', 'Kính Đổi Màu Chống Ánh Sáng Xanh Zinmy 1.60 AS Duramax', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (524, 125, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/15-copy.jpg', 'Kính Đổi Màu Chống Ánh Sáng Xanh Zinmy 1.60 AS Duramax', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (525, 126, 'https://kinhmateyeplus.com/wp-content/uploads/2022/06/HOGA-1.56-HMC-2-TRONG.jpg', 'Tròng kính 1.56 - HOGA 1.56 UV400 SHMC- PLUS', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (526, 126, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/trong-element.jpg', 'Tròng kính 1.56 - HOGA 1.56 UV400 SHMC- PLUS', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (527, 126, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/2-trong.png', 'Tròng kính 1.56 - HOGA 1.56 UV400 SHMC- PLUS', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (528, 126, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/8-copy.jpg', 'Tròng kính 1.56 - HOGA 1.56 UV400 SHMC- PLUS', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (529, 127, 'https://kinhmateyeplus.com/wp-content/uploads/2017/09/20250506-095454.jpg', 'Tròng Kính Đổi Màu Cận Viễn Hoga Hàn Quốc', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (530, 127, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/trong-element.jpg', 'Tròng Kính Đổi Màu Cận Viễn Hoga Hàn Quốc', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (531, 127, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_8457.jpg', 'Tròng Kính Đổi Màu Cận Viễn Hoga Hàn Quốc', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (532, 127, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_2316.png', 'Tròng Kính Đổi Màu Cận Viễn Hoga Hàn Quốc', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (533, 128, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/es-trans-gen-8-1.50.jpg', 'Tròng Kính Đổi Màu Element 1.61 AS', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (534, 128, 'https://kinhmateyeplus.com/wp-content/uploads/2017/09/20250506-095454.jpg', 'Tròng Kính Đổi Màu Element 1.61 AS', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (535, 128, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/element-1.61-1.png', 'Tròng Kính Đổi Màu Element 1.61 AS', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (536, 128, 'https://kinhmateyeplus.com/wp-content/uploads/2022/11/element-1.61.png', 'Tròng Kính Đổi Màu Element 1.61 AS', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (537, 129, 'https://kinhmateyeplus.com/wp-content/uploads/2022/03/ess-crizal-pre-1.png', 'Tròng Siêu Mỏng Essilor Crizal Prevencia chiết suất 1.67', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (538, 129, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/prevencia.png', 'Tròng Siêu Mỏng Essilor Crizal Prevencia chiết suất 1.67', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (539, 129, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/rock-1.png', 'Tròng Siêu Mỏng Essilor Crizal Prevencia chiết suất 1.67', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (540, 129, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/3-copy.jpg', 'Tròng Siêu Mỏng Essilor Crizal Prevencia chiết suất 1.67', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (541, 130, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0827-1.jpg', 'Tròng Kính Đổi Màu Chemi 1.56 Hàn Quốc', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (542, 130, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0777-1.jpg', 'Tròng Kính Đổi Màu Chemi 1.56 Hàn Quốc', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (543, 130, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/adadasdasd.jpg', 'Tròng Kính Đổi Màu Chemi 1.56 Hàn Quốc', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (544, 130, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0801-1.jpg', 'Tròng Kính Đổi Màu Chemi 1.56 Hàn Quốc', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (545, 130, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/17-copy.jpg', 'Tròng Kính Đổi Màu Chemi 1.56 Hàn Quốc', FALSE, 4, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (546, 131, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/es-trans-gen-8-1.50.jpg', 'Tròng Đổi Màu Essilor Classic 1.56', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (547, 131, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/es-trans-gen-8-KHOI.jpg', 'Tròng Đổi Màu Essilor Classic 1.56', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (548, 131, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_9901-1.png', 'Tròng Đổi Màu Essilor Classic 1.56', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (549, 131, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/10-copy.jpg', 'Tròng Đổi Màu Essilor Classic 1.56', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (550, 132, 'https://kinhmateyeplus.com/wp-content/uploads/2022/08/vision-web.png', 'Tròng Kính Đổi Màu Vision X 1.60 AS SUN PROTECT CHỐNG ÁNH SÁNG XANH', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (551, 132, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/chemi-1.67.jpg', 'Tròng Kính Đổi Màu Vision X 1.60 AS SUN PROTECT CHỐNG ÁNH SÁNG XANH', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (552, 132, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/10-copy.jpg', 'Tròng Kính Đổi Màu Vision X 1.60 AS SUN PROTECT CHỐNG ÁNH SÁNG XANH', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (553, 132, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/17-copy.jpg', 'Tròng Kính Đổi Màu Vision X 1.60 AS SUN PROTECT CHỐNG ÁNH SÁNG XANH', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (554, 132, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/vision.png', 'Tròng Kính Đổi Màu Vision X 1.60 AS SUN PROTECT CHỐNG ÁNH SÁNG XANH', FALSE, 4, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (555, 133, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/es-trans-gen-8-1.50.jpg', 'Tròng Đổi Màu Essilor Transition Gen 8 STYLE COLORS 1.60 AS', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (556, 133, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0866-1.jpg', 'Tròng Đổi Màu Essilor Transition Gen 8 STYLE COLORS 1.60 AS', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (557, 133, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0801-1.jpg', 'Tròng Đổi Màu Essilor Transition Gen 8 STYLE COLORS 1.60 AS', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (558, 133, 'https://kinhmateyeplus.com/wp-content/uploads/2022/03/bang-mau.png', 'Tròng Đổi Màu Essilor Transition Gen 8 STYLE COLORS 1.60 AS', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (559, 134, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/es-trans-gen-8-KHOI.jpg', 'Tròng Kính Đổi Màu Element 1.67', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (560, 134, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/trong-element.jpg', 'Tròng Kính Đổi Màu Element 1.67', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (561, 134, 'https://kinhmateyeplus.com/wp-content/uploads/2022/03/trong-element.jpg', 'Tròng Kính Đổi Màu Element 1.67', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (562, 134, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0844-copy.jpg', 'Tròng Kính Đổi Màu Element 1.67', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (563, 135, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/es-trans-gen-8-1.50.jpg', 'Tròng Đổi Màu Essilor Transition Gen 8 STYLE COLORS 1.50 SPH STYLE COLORS', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (564, 135, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/es-trans-gen-8-KHOI.jpg', 'Tròng Đổi Màu Essilor Transition Gen 8 STYLE COLORS 1.50 SPH STYLE COLORS', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (565, 135, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/trong-element.jpg', 'Tròng Đổi Màu Essilor Transition Gen 8 STYLE COLORS 1.50 SPH STYLE COLORS', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (566, 135, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0780-1.jpg', 'Tròng Đổi Màu Essilor Transition Gen 8 STYLE COLORS 1.50 SPH STYLE COLORS', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (567, 136, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/es-trans-gen-8-KHOI.jpg', 'Tròng Đổi Màu Essilor Transition Gen 8 1.59 AS Airwear Màu Khói', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (568, 136, 'https://kinhmateyeplus.com/wp-content/uploads/2017/09/20250506-095454.jpg', 'Tròng Đổi Màu Essilor Transition Gen 8 1.59 AS Airwear Màu Khói', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (569, 136, 'https://kinhmateyeplus.com/wp-content/uploads/2022/03/mau-xam-2.png', 'Tròng Đổi Màu Essilor Transition Gen 8 1.59 AS Airwear Màu Khói', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (570, 136, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_2316.png', 'Tròng Đổi Màu Essilor Transition Gen 8 1.59 AS Airwear Màu Khói', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (571, 137, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/es-trans-gen-8-1.50.jpg', 'Kính Đổi Màu Chống Ánh Sáng Xanh Zinmy 1.60 AS Duramax', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (572, 137, 'https://kinhmateyeplus.com/wp-content/uploads/2022/11/Zinmy-doi-mau.png', 'Kính Đổi Màu Chống Ánh Sáng Xanh Zinmy 1.60 AS Duramax', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (573, 137, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/chemi-1.67.jpg', 'Kính Đổi Màu Chống Ánh Sáng Xanh Zinmy 1.60 AS Duramax', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (574, 137, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/zinmy-160.png', 'Kính Đổi Màu Chống Ánh Sáng Xanh Zinmy 1.60 AS Duramax', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (575, 138, 'https://kinhmateyeplus.com/wp-content/uploads/2017/09/20250506-095454.jpg', 'Tròng Kính Đổi Màu Cận Viễn Hoga Hàn Quốc', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (576, 138, 'https://kinhmateyeplus.com/wp-content/uploads/2023/06/Group-4-copy-3.jpg', 'Tròng Kính Đổi Màu Cận Viễn Hoga Hàn Quốc', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (577, 138, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_0827-1.jpg', 'Tròng Kính Đổi Màu Cận Viễn Hoga Hàn Quốc', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (578, 138, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/IMG_8457.jpg', 'Tròng Kính Đổi Màu Cận Viễn Hoga Hàn Quốc', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (579, 139, 'https://kinhmateyeplus.com/wp-content/uploads/2017/09/20250506-095454.jpg', 'Tròng Kính Đổi Màu Element 1.61 AS', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (580, 139, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/element-1.61-1.png', 'Tròng Kính Đổi Màu Element 1.61 AS', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (581, 139, 'https://kinhmateyeplus.com/wp-content/uploads/2022/11/element-1.61.png', 'Tròng Kính Đổi Màu Element 1.61 AS', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (582, 139, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/9-copy-1.jpg', 'Tròng Kính Đổi Màu Element 1.61 AS', FALSE, 3, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (583, 140, 'https://kinhmateyeplus.com/wp-content/uploads/2023/01/hoga-sapphire.png', 'Kính Đổi Màu Chống Ánh Sáng Xanh HOGA Sapphire 1.56', TRUE, 0, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (584, 140, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/Hoga-saphire.png', 'Kính Đổi Màu Chống Ánh Sáng Xanh HOGA Sapphire 1.56', FALSE, 1, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (585, 140, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/2-trong.png', 'Kính Đổi Màu Chống Ánh Sáng Xanh HOGA Sapphire 1.56', FALSE, 2, NOW());
INSERT INTO product_image (id, product_id, image_url, alt_text, is_primary, display_order, created_at) 
VALUES (586, 140, 'https://kinhmateyeplus.com/wp-content/uploads/2024/11/3-copy.jpg', 'Kính Đổi Màu Chống Ánh Sáng Xanh HOGA Sapphire 1.56', FALSE, 3, NOW());

COMMIT;
SET FOREIGN_KEY_CHECKS = 1;
SET AUTOCOMMIT = 1;

-- Verify Data
SELECT 'Categories' AS Table_Name, COUNT(*) AS Total_Records FROM category
UNION ALL
SELECT 'Products', COUNT(*) FROM product
UNION ALL
SELECT 'Product Images', COUNT(*) FROM product_image;
