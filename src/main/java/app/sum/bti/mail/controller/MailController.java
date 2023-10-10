package app.sum.bti.mail.controller;

import app.sum.bti.mail.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MailController {

    private final MailService emailService;


    @PostMapping("/checkEmails")
    @ResponseBody
    public Map<String,Object> sendEmail(@RequestParam(value = "EmailCheck") String EmailCheck,
                                        HttpServletRequest request) {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        String confirmCode = emailService.getCode();

        String to = EmailCheck;
        String subject = "SumBti 인증코드 입니다.";
        String text = confirmCode;

        try {
            emailService.sendEmailWithAttachment(to, subject, text);
            resultMap.put("resultCode",200);
            HttpSession session = request.getSession();
            //세션에 코드저장
            session.setAttribute("confirmCode",confirmCode);
            //html에 코드 반환
            resultMap.put("confirmCode",confirmCode);
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            resultMap.put("resultCode",500);
        }
        return resultMap;
    }
}
