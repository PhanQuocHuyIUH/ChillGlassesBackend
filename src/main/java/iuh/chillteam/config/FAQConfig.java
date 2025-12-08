package iuh.chillteam.config;

import org.springframework.stereotype.Component;

import java.util.*;

/**
 * FAQ Configuration - Pre-defined responses for common questions
 * Giảm cost OpenAI API bằng cách trả lời trực tiếp các câu hỏi thường gặp
 */
@Component
public class FAQConfig {
    
    private final Map<String, FAQResponse> faqMap;
    
    public FAQConfig() {
        this.faqMap = initializeFAQs();
    }
    
    /**
     * Check if message matches any FAQ pattern
     * @param message User's message
     * @return FAQ response or null if not matched
     */
    public FAQResponse findMatchingFAQ(String message) {
        String normalized = normalizeMessage(message);
        
        // Check exact matches first
        for (Map.Entry<String, FAQResponse> entry : faqMap.entrySet()) {
            if (normalized.equals(entry.getKey())) {
                return entry.getValue();
            }
        }
        
        // Check pattern matches
        for (Map.Entry<String, FAQResponse> entry : faqMap.entrySet()) {
            if (normalized.matches(entry.getKey())) {
                return entry.getValue();
            }
        }
        
        return null;
    }
    
    /**
     * Normalize message for matching
     */
    private String normalizeMessage(String message) {
        return message.toLowerCase()
                     .trim()
                     .replaceAll("[!.?,;:]+$", "") // Remove trailing punctuation
                     .replaceAll("\\s+", " ");      // Normalize whitespace
    }
    
    /**
     * Initialize all FAQ patterns and responses
     */
    private Map<String, FAQResponse> initializeFAQs() {
        Map<String, FAQResponse> faqs = new LinkedHashMap<>();
        
        // ==================== CHÀO HỎI & GIỚI THIỆU ====================
        
        // Chào hỏi cơ bản
        addFAQ(faqs, "^(xin )?chào( bạn| bn)?$", 
            "Xin chào! 👋 Tôi là trợ lý AI của ChillGlasses. Tôi có thể giúp bạn tìm kính phù hợp với khuôn mặt, phong cách và ngân sách. Bạn đang tìm kiếm loại kính nào?");
        
        addFAQ(faqs, "^(hi|hello|hey)( there)?$",
            "Hello! 👋 Welcome to ChillGlasses! Tôi có thể tư vấn cho bạn về kính mắt thời trang. Bạn cần hỗ trợ gì?");
        
        addFAQ(faqs, "^chào (buổi sáng|buổi chiều|buổi tối)$",
            "Xin chào! 🌞 Chúc bạn một ngày tốt lành! Hôm nay bạn muốn tìm kiếm loại kính nào?");
        
        // Hỏi thăm sức khỏe
        addFAQ(faqs, "^(bạn |bn )?khỏe (không|ko|k|hem)( bạn| bn)?$",
            "Tôi khỏe, cảm ơn bạn! 😊 Còn bạn thì sao? Hôm nay tôi có thể giúp gì cho bạn về kính mắt?");
        
        addFAQ(faqs, "^(bạn |bn )?có khỏe (không|ko|k|hem)$",
            "Tôi rất khỏe! Cảm ơn bạn đã hỏi thăm 💚 Tôi luôn sẵn sàng tư vấn kính cho bạn đây!");
        
        addFAQ(faqs, "^(how are you|how r u)$",
            "I'm doing great, thank you! 😊 How can I help you find the perfect glasses today?");
        
        // Hỏi về bot
        addFAQ(faqs, "^(bạn |bn )?là ai( vậy| thế)?$",
            "Tôi là trợ lý AI của ChillGlasses - chuyên gia tư vấn kính mắt thời trang! 🤖✨ Tôi được huấn luyện để giúp bạn tìm kính phù hợp nhất với khuôn mặt và phong cách của bạn.");
        
        addFAQ(faqs, "^(bạn |bn )?tên (là )?gì$",
            "Tôi là ChillGlasses AI Assistant 🤖 - trợ lý ảo chuyên về kính mắt. Tôi ở đây để giúp bạn tìm được chiếc kính hoàn hảo!");
        
        addFAQ(faqs, "^(what|who) (is your name|are you)$",
            "I'm ChillGlasses AI Assistant 🤖 - your personal eyewear consultant! I'm here to help you find the perfect glasses.");
        
        // ==================== CÁM ƠN & TẠM BIỆT ====================
        
        // Cảm ơn
        addFAQ(faqs, "^(cảm ơn|cám ơn|thank you|thanks|thank u|tks)( bạn| bn| nhiều| nhé)?$",
            "Rất vui được giúp đỡ bạn! 😊 Nếu còn thắc mắc gì về kính mắt, đừng ngại hỏi tôi nhé!");
        
        addFAQ(faqs, "^(cảm ơn|cám ơn) (đã|da) (giúp đỡ|giúp|tư vấn|support)$",
            "Không có gì! 💙 Đó là nhiệm vụ của tôi mà! Chúc bạn tìm được chiếc kính ưng ý nhất!");
        
        addFAQ(faqs, "^ok (cảm ơn|cám ơn|thanks)$",
            "You're welcome! 😊 Chúc bạn một ngày tuyệt vời với ChillGlasses!");
        
        // Tạm biệt
        addFAQ(faqs, "^(tạm biệt|bye|goodbye|see you|bb|bai|bai bai)$",
            "Tạm biệt! 👋 Hẹn gặp lại bạn sớm! Đừng quên ghé thăm cửa hàng ChillGlasses nhé! 😊");
        
        addFAQ(faqs, "^(hẹn gặp lại|see ya)$",
            "Hẹn gặp lại bạn! 🌟 Chúc bạn luôn tự tin với phong cách của mình!");
        
        // ==================== THÔNG TIN CỬA HÀNG ====================
        
        // Giờ mở cửa
        addFAQ(faqs, ".*(giờ mở cửa|mấy giờ mở|open time|opening hours).*",
            "🕐 ChillGlasses mở cửa:\n• Thứ 2 - Thứ 6: 8:00 - 21:00\n• Thứ 7 - Chủ nhật: 9:00 - 22:00\n\nChúng tôi luôn sẵn sàng phục vụ bạn!");
        
        // Địa chỉ
        addFAQ(faqs, ".*(địa chỉ|ở đâu|chỗ nào|address|location).*",
            "📍 Địa chỉ ChillGlasses:\n12 Nguyễn Văn Bảo, Phường 4, Gò Vấp, TP.HCM\n\n🚗 Bạn có thể đến bằng xe bus, xe ôm hoặc ô tô. Có bãi đỗ xe miễn phí!");
        
        // Liên hệ
        addFAQ(faqs, ".*(liên (hệ|he)|contact|hotline|số điện thoại|phone|sdt|số dt).*",
            "📞 Liên hệ ChillGlasses:\n• Hotline: 1900-xxxx\n• Email: support@chillglasses.com\n• Facebook: fb.com/chillglasses\n• Instagram: @chillglasses\n\nChúng tôi luôn sẵn sàng hỗ trợ bạn 24/7!");
        
        // ==================== CHÍNH SÁCH ====================
        
        // Giao hàng
        addFAQ(faqs, ".*(giao hàng|vận chuyển|ship|delivery).*",
            "🚚 Chính sách giao hàng ChillGlasses:\n• Miễn phí giao hàng toàn quốc cho đơn từ 500k\n• Giao hàng nhanh 2-3 ngày (nội thành TP.HCM)\n• Giao hàng toàn quốc 3-5 ngày\n• Kiểm tra hàng trước khi thanh toán\n• Đóng gói cẩn thận, bảo vệ sản phẩm");
        
        // Đổi trả
        addFAQ(faqs, ".*(đổi trả|hoàn trả|return|refund).*",
            "🔄 Chính sách đổi trả:\n• Đổi trả trong 30 ngày nếu sản phẩm lỗi\n• Hoàn tiền 100% nếu không hài lòng (trong 7 ngày)\n• Đổi size/màu miễn phí trong 15 ngày\n• Điều kiện: còn nguyên tem, hộp, chưa qua sử dụng\n• Hỗ trợ đổi trả tại nhà");
        
        // Bảo hành
        addFAQ(faqs, ".*(bảo hành|warranty|guarantee).*",
            "🛡️ Chính sách bảo hành:\n• Bảo hành 12 tháng với lỗi nhà sản xuất\n• Miễn phí vệ sinh, bảo dưỡng trọn đời\n• Sửa chữa, thay thế linh kiện chính hãng\n• Hỗ trợ kỹ thuật 24/7\n• Bảo hành tại tất cả chi nhánh");
        
        // Thanh toán
        addFAQ(faqs, ".*(thanh toán|payment|pay|trả tiền).*",
            "💳 Phương thức thanh toán:\n• COD (thanh toán khi nhận hàng)\n• Chuyển khoản ngân hàng\n• Ví điện tử (MoMo, ZaloPay, VNPay)\n• Thẻ tín dụng/ghi nợ (Visa, Master, JCB)\n• Trả góp 0% (từ 3-12 tháng)\n\nAn toàn, bảo mật 100%!");
        
        // ==================== VỀ SẢN PHẨM ====================
        
        // Giá cả
        addFAQ(faqs, ".*(giá|bao nhiêu tiền|price|cost).*",
            "💰 ChillGlasses có đa dạng sản phẩm với nhiều mức giá:\n• Phổ thông: 200k - 500k\n• Cao cấp: 500k - 1.5 triệu\n• Premium: 1.5 triệu - 3 triệu\n• Luxury: Trên 3 triệu\n\nBạn muốn tìm kính trong tầm giá nào? Tôi sẽ tư vấn cụ thể cho bạn!");
        
        // Chất liệu
        addFAQ(faqs, ".*(chất liệu|material|made of).*",
            "🔨 Chất liệu kính tại ChillGlasses:\n• Gọng: Titan, thép không gỉ, nhựa TR90, acetate\n• Tròng: Kính cường lực, polycarbonate, resin\n• Lớp phủ: Chống tia UV400, chống xước, chống bám bẩn\n\nTất cả đều là chất liệu cao cấp, an toàn cho sức khỏe!");
        
        // Có sẵn không
        addFAQ(faqs, ".*(còn hàng|có sẵn|in stock|available).*",
            "✅ Tất cả sản phẩm trên website đều có sẵn tại kho!\n\nNếu bạn muốn kiểm tra sản phẩm cụ thể, hãy cho tôi biết:\n• Khuôn mặt của bạn (tròn/vuông/oval...)\n• Phong cách yêu thích (năng động/lịch sự...)\n• Ngân sách dự kiến\n\nTôi sẽ gợi ý những mẫu kính phù hợp nhất!");
        
        // ==================== TƯ VẤN & HỖ TRỢ ====================
        
        // Không biết chọn gì
        addFAQ(faqs, ".*(không biết|ko biết|chưa biết|confused|not sure).*(chọn|lựa).*",
            "🤔 Đừng lo! Tôi sẽ giúp bạn!\n\nHãy cho tôi biết:\n1️⃣ Khuôn mặt: tròn/vuông/oval/trái xoan?\n2️⃣ Mục đích: thời trang/đi học/đi làm/du lịch?\n3️⃣ Phong cách: năng động/lịch sự/cá tính/nữ tính?\n4️⃣ Ngân sách: khoảng bao nhiêu?\n\nVới thông tin này, tôi sẽ gợi ý 3 mẫu kính tuyệt vời cho bạn! 😊");
        
        // Cần tư vấn
        addFAQ(faqs, "^(tư vấn|tu van|help|support|giúp)( cho tôi| cho mình| với)?$",
            "Tôi sẵn sàng tư vấn! 💡\n\nBạn muốn tôi tư vấn về:\n🔸 Kính phù hợp với khuôn mặt?\n🔸 Màu sắc phù hợp với tông da?\n🔸 Phong cách thời trang?\n🔸 Sản phẩm trong ngân sách?\n🔸 So sánh các mẫu kính?\n\nHãy nói cho tôi biết bạn cần gì nhé!");
        
        // Hỏi về khuyến mãi
        addFAQ(faqs, ".*(khuyến mãi|giảm giá|sale|promotion|discount|voucher).*",
            "🎉 Khuyến mãi hot tại ChillGlasses:\n• Giảm 20% cho khách hàng mới\n• Mua 2 tặng 1 phụ kiện\n• Freeship toàn quốc cho đơn từ 500k\n• Tích điểm đổi quà\n• Flash sale cuối tuần\n\nĐăng ký thành viên để nhận ưu đãi độc quyền!");
        
        // ==================== PHẢN HỒI TIÊU CỰC ====================
        
        // Không hài lòng
        addFAQ(faqs, ".*(không hài lòng|ko hài lòng|tệ|kém|not good|bad).*",
            "Tôi rất xin lỗi vì trải nghiệm chưa tốt của bạn 😔\n\nĐể phục vụ bạn tốt hơn, vui lòng liên hệ:\n📞 Hotline: 1900-xxxx\n📧 Email: support@chillglasses.com\n\nChúng tôi sẽ giải quyết vấn đề của bạn trong 24h!");
        
        // ==================== CÂU HỎI CHUNG ====================
        
        // OK / Ừ / Được
        addFAQ(faqs, "^(ok|oke|okay|okie|okiee?|ừ|uhm|được|dc)$",
            "Tuyệt vời! 😊 Bạn có câu hỏi gì khác về kính mắt không?");
        
        // Có
        addFAQ(faqs, "^(có|yes|yep|yeah)$",
            "Tốt! Tôi đang lắng nghe đây! 👂 Bạn cần tôi giúp gì nào?");
        
        // Không
        addFAQ(faqs, "^(không|ko|k|no|nope)$",
            "Không sao! Nếu cần tư vấn gì về kính mắt, cứ thoải mái hỏi tôi nhé! 😊");
        
        // Haha / hehe
        addFAQ(faqs, "^(haha|hehe|hihi|lol|hehe+|haha+)$",
            "😄 Vui là tốt rồi! Có gì tôi có thể giúp bạn về kính mắt không?");
        
        // Test
        addFAQ(faqs, "^(test|testing|thử)$",
            "Hệ thống hoạt động tốt! ✅ Bạn có muốn thử tính năng tư vấn kính không?");
        
        return faqs;
    }
    
    /**
     * Helper method to add FAQ
     */
    private void addFAQ(Map<String, FAQResponse> faqs, String pattern, String response) {
        faqs.put(pattern, new FAQResponse(response, "faq"));
    }
    
    /**
     * FAQ Response class
     */
    public static class FAQResponse {
        private final String message;
        private final String responseType;
        
        public FAQResponse(String message, String responseType) {
            this.message = message;
            this.responseType = responseType;
        }
        
        public String getMessage() {
            return message;
        }
        
        public String getResponseType() {
            return responseType;
        }
    }
}
