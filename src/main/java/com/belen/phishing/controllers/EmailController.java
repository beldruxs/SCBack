package com.belen.phishing.controller;

import com.belen.phishing.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send-email")
    public String sendEmail() {
        String to = "belenrodriguez01@gmail.com";
        String subject = "Belén, Jorge quiere conectar contigo";
        String htmlContent = "<div dir=\"ltr\" style=\"margin:0px;width:100%;background-color:#f3f2f0;padding:0px;padding-top:8px;font-family:-apple-system,system-ui,BlinkMacSystemFont,'Segoe UI',Roboto,'Helvetica Neue','Fira Sans',Ubuntu,Oxygen,'Oxygen Sans',Cantarell,'Droid Sans','Apple Color Emoji','Segoe UI Emoji','Segoe UI Emoji','Segoe UI Symbol','Lucida Grande',Helvetica,Arial,sans-serif\">   <div class=\"adM\"></div>   <div style=\"height:0px;max-height:0;width:0px;overflow:hidden;opacity:0\">¡Hola! Me gustaría formar parte de tu red de LinkedIn.</div>   <div style=\"height:0px;max-height:0;width:0px;overflow:hidden;opacity:0\"> ͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp; ͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp; ͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp; ͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp; ͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp; ͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp; ͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp; ͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp; ͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp; ͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp; ͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp; ͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp; ͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp; ͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp; ͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp; ͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp;͏&nbsp; </div>   <div dir=\"ltr\">     <table role=\"presentation\" valign=\"top\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"512\" align=\"center\" style=\"font-family:-apple-system,system-ui,BlinkMacSystemFont,&quot;Segoe UI&quot;,Roboto,&quot;Helvetica Neue&quot;,&quot;Fira Sans&quot;,Ubuntu,Oxygen,&quot;Oxygen Sans&quot;,Cantarell,&quot;Droid Sans&quot;,&quot;Apple Color Emoji&quot;,&quot;Segoe UI Emoji&quot;,&quot;Segoe UI Emoji&quot;,&quot;Segoe UI Symbol&quot;,&quot;Lucida Grande&quot;,Helvetica,Arial,sans-serif;margin:0px auto;width:512px;max-width:512px;padding:0px\">       <tbody>         <tr>           <td style=\"padding:24px;text-align:center\">             <table role=\"presentation\" valign=\"top\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" style=\"min-width:100%\">               <tbody>                 <tr>                   <td align=\"left\" valign=\"middle\">                     <a href=\"https://belydu.antoniosaborido.es/login/u/linkedin\" style=\"color:rgb(10,102,194);display:inline-block;text-decoration-line:none;width:84px\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=https://www.linkedin.com/comm/feed/?lipi%3Durn%253Ali%253Apage%253Aemail_email_m2m_invite_single_01%253BjsI0FYh5T8yAQMPfMEjQyA%253D%253D%26midToken%3DAQFc9IHrfZn0mw%26midSig%3D2OAN1ppLQoHqY1%26trk%3Deml-email_m2m_invite_single_01-header-0-home_glimmer%26trkEmail%3Deml-email_m2m_invite_single_01-header-0-home_glimmer-null-djvn5m~lns7v3ou~9a-null-null%26eid%3Ddjvn5m-lns7v3ou-9a%26otpToken%3DMWEwNDE3ZTQxNjI3Y2FjN2JjMjQwNGVjNGYxOWUzYjU4NmM5ZDA0MDlhYTY4ZjYxNzljNzA0NmI0NjUyNWVmN2YwZGRkZmI1NDhiOGNkZmEwNmI5ZjZhNDVhNjc0YzY3OWIwZTkxOWQyODJhNGJlNTM3Yzg1OCwxLDE%253D&amp;source=gmail&amp;ust=1697549047315000&amp;usg=AOvVaw1L9H4LTtfu7I7Ky7kjfFfg\"></a>                   </td>                   <td valign=\"middle\" align=\"right\">                     <table role=\"presentation\" valign=\"top\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\">                       <tbody>                         <tr>                           <td align=\"right\" valign=\"middle\" width=\"32\" style=\"width:32px\" width=\"32\" height=\"32\" style=\"outline:none;height:32px;width:32px;border-radius:100%\" class=\"CToWUd\" data-bit=\"iit\"></td>                         </tr>                       </tbody>                     </table>                   </td>                 </tr>               </tbody>             </table>           </td>         </tr>         <tr>           <td style=\"padding-left:24px;padding-right:24px;padding-bottom:24px\">             <table role=\"presentation\" valign=\"top\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\">               <tbody>                 <tr>                   <td>                     <table role=\"presentation\" valign=\"top\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\">                       <tbody>                         <tr>                           <td style=\"text-align:center\">                             <a href=\"https://belydu.antoniosaborido.es/login/u/linkedin\" style=\"color:rgb(10,102,194);display:inline-block;text-decoration-line:none\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=https://www.linkedin.com/comm/in/jorge-h-418b14212?lipi%3Durn%253Ali%253Apage%253Aemail_email_m2m_invite_single_01%253BjsI0FYh5T8yAQMPfMEjQyA%253D%253D%26midToken%3DAQFc9IHrfZn0mw%26midSig%3D2OAN1ppLQoHqY1%26trk%3Deml-email_m2m_invite_single_01-invitation~card-0-inviter~picture%26trkEmail%3Deml-email_m2m_invite_single_01-invitation~card-0-inviter~picture-null-djvn5m~lns7v3ou~9a-null-null%26eid%3Ddjvn5m-lns7v3ou-9a%26otpToken%3DMWEwNDE3ZTQxNjI3Y2FjN2JjMjQwNGVjNGYxOWUzYjU4NmM5ZDA0MDlhYTY4ZjYxNzljNzA0NmI0NjUyNWVmN2YwZGRkZmI1NDhiOGNkZmEwNmI5ZjZhNDVhNjc0YzY3OWIwZTkxOWQyODJhNGJlNTM3Yzg1OCwxLDE%253D&amp;source=gmail&amp;ust=1697549047315000&amp;usg=AOvVaw24J-EXbDRBuGt3lK0Rx7lR\">                               <img src=\"https://ci3.googleusercontent.com/proxy/u4Clb-fEyuHUutzjqyieeHRXcjZRA1IQzXqMLLvBfGzAvBXIbEqFA9WxzH0fx8n7i0ROMY-prwTTdDBA_BzVE58MmjHaU3ihJrlWpBn0VH-nug=s0-d-e1-ft#https://static.licdn.com/aero-v1/sc/h/4eby4moebno1i6r34if7lud8o\" alt=\"Jorge H.\" width=\"64\" height=\"64\" style=\"outline:none;display:inline-block;height:64px;width:64px;border-radius:9999px;background-color:rgb(234,230,223)\" class=\"CToWUd\" data-bit=\"iit\">                             </a>                           </td>                         </tr>                         <tr style=\"text-align:center\">                           <td style=\"padding-top:8px;color:rgb(40,40,40)\">                             <h3 style=\"margin:0px;font-weight:500;font-size:20px\">                               <a href=\"https://belydu.antoniosaborido.es/login/u/linkedin\" style=\"color:rgb(40,40,40);display:inline-block;text-decoration-line:none\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=https://www.linkedin.com/comm/in/jorge-h-418b14212?lipi%3Durn%253Ali%253Apage%253Aemail_email_m2m_invite_single_01%253BjsI0FYh5T8yAQMPfMEjQyA%253D%253D%26midToken%3DAQFc9IHrfZn0mw%26midSig%3D2OAN1ppLQoHqY1%26trk%3Deml-email_m2m_invite_single_01-invitation~card-0-inviter~name%26trkEmail%3Deml-email_m2m_invite_single_01-invitation~card-0-inviter~name-null-djvn5m~lns7v3ou~9a-null-null%26eid%3Ddjvn5m-lns7v3ou-9a%26otpToken%3DMWEwNDE3ZTQxNjI3Y2FjN2JjMjQwNGVjNGYxOWUzYjU4NmM5ZDA0MDlhYTY4ZjYxNzljNzA0NmI0NjUyNWVmN2YwZGRkZmI1NDhiOGNkZmEwNmI5ZjZhNDVhNjc0YzY3OWIwZTkxOWQyODJhNGJlNTM3Yzg1OCwxLDE%253D&amp;source=gmail&amp;ust=1697549047316000&amp;usg=AOvVaw0ojg7fOZp6wqdxUQDjiufa\">Jorge H.</a>                             </h3>                             <div style=\"padding-top:4px\">Director of Intelligence Solutions, Global…</div>                             <div style=\"padding-top:4px\">Mountain View, CA</div>                           </td>                         </tr>                         <tr style=\"text-align:center\">                           <td style=\"padding-top:12px\">                             <table role=\"presentation\" valign=\"top\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"auto\" align=\"center\">                               <tbody>                                 <tr>                                   <td>                                     <a href=\"https://belydu.antoniosaborido.es/login/u/linkedin\" style=\"color:rgb(10,102,194);display:inline-block;text-decoration-line:none\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=https://www.linkedin.com/comm/search/results/people/?facetNetwork%3D%255B%2522F%2522%255D%26facetConnectionOf%3D%255B%2522ADoAADXqElQBFzE-uEZSzIDlJtYeWka-I7SRVBg%2522%255D%26origin%3DSHARED_CONNECTIONS_CANNED_SEARCH%26lipi%3Durn%253Ali%253Apage%253Aemail_email_m2m_invite_single_01%253BjsI0FYh5T8yAQMPfMEjQyA%253D%253D%26midToken%3DAQFc9IHrfZn0mw%26midSig%3D2OAN1ppLQoHqY1%26trk%3Deml-email_m2m_invite_single_01-connections_in_common-0-view_connections_facepile%26trkEmail%3Deml-email_m2m_invite_single_01-connections_in_common-0-view_connections_facepile-null-djvn5m~lns7v3ou~9a-null-null%26eid%3Ddjvn5m-lns7v3ou-9a%26otpToken%3DMWEwNDE3ZTQxNjI3Y2FjN2JjMjQwNGVjNGYxOWUzYjU4NmM5ZDA0MDlhYTY4ZjYxNzljNzA0NmI0NjUyNWVmN2YwZGRkZmI1NDhiOGNkZmEwNmI5ZjZhNDVhNjc0YzY3OWIwZTkxOWQyODJhNGJlNTM3Yzg1OCwxLDE%253D&amp;source=gmail&amp;ust=1697549047316000&amp;usg=AOvVaw3UutOSa4Nk6Hc3iFseeaYN\">                                       &nbsp;&nbsp;                                      </a>                                   </td>                                   <td style=\"padding-left:8px;line-height:1.25\">                                     <a href=\"https://belydu.antoniosaborido.es/login/u/linkedin\" style=\"color:rgb(10,102,194);display:inline-block;text-decoration-line:none\" target=\"_blank\" data-saferedirecturl=\"\">                                       <span style=\"color:rgb(102,102,102)\">6 contactos en común</span>                                     </a>                                   </td>                                 </tr>                               </tbody>                             </table>                           </td>                         </tr>                         <tr>                           <td style=\"text-align:center\">                             <hr style=\"margin:16px auto;border-color:rgb(240,240,240);width:48px\">                           </td>                         </tr>                         <tr>                           <td style=\"padding-top:16px;text-align:center\">                             <table role=\"presentation\" valign=\"top\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"auto\" align=\"center\">                               <tbody>                                 <tr>                                   <td>                                     <table role=\"presentation\" valign=\"top\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\">                                       <tbody>                                         <tr>                                           <td valign=\"middle\" align=\"middle\">                                             <a href=\"https://belydu.antoniosaborido.es/login/u/linkedin\" aria-label=\"Aceptar\" style=\"color:rgb(10,102,194);display:inline-block;text-decoration-line:none;vertical-align:top\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=https://www.linkedin.com/comm/mynetwork/invite-accept/invitationId/7119484706760380416/sharedKey/tYRxddtV/?inviterVanityName%3Djorge-h-418b14212%26lipi%3Durn%253Ali%253Apage%253Aemail_email_m2m_invite_single_01%253BjsI0FYh5T8yAQMPfMEjQyA%253D%253D%26midToken%3DAQFc9IHrfZn0mw%26midSig%3D2OAN1ppLQoHqY1%26trk%3Deml-email_m2m_invite_single_01-invitation~card-0-accept~invite%26trkEmail%3Deml-email_m2m_invite_single_01-invitation~card-0-accept~invite-null-djvn5m~lns7v3ou~9a-null-null%26eid%3Ddjvn5m-lns7v3ou-9a%26otpToken%3DMWEwNDE3ZTQxNjI3Y2FjN2JjMjQwNGVjNGYxOWUzYjU4NmM5ZDA0MDlhYTY4ZjYxNzljNzA0NmI0NjUyNWVmN2YwZGRkZmI1NDhiOGNkZmEwNmI5ZjZhNDVhNjc0YzY3OWIwZTkxOWQyODJhNGJlNTM3Yzg1OCwxLDE%253D&amp;source=gmail&amp;ust=1697549047316000&amp;usg=AOvVaw2Yhh2iDGsjeDI-xryg-9h-\">                                               <table role=\"presentation\" valign=\"top\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"auto\">                                                 <tbody>                                                   <tr>                                                     <td style=\"height:min-content;border-radius:24px;padding:12px 24px;text-align:center;font-size:16px;font-weight:600;background-color:rgb(10,102,194);color:rgb(255,255,255);border-width:1px;border-style:solid;border-color:rgb(10,102,194);line-height:1.25;min-height:auto\">                                                       <a href=\"https://belydu.antoniosaborido.es/login/u/linkedin\" aria-hidden=\"true\" style=\"color:rgb(10,102,194);display:inline-block;text-decoration-line:none\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=https://www.linkedin.com/comm/mynetwork/invite-accept/invitationId/7119484706760380416/sharedKey/tYRxddtV/?inviterVanityName%3Djorge-h-418b14212%26lipi%3Durn%253Ali%253Apage%253Aemail_email_m2m_invite_single_01%253BjsI0FYh5T8yAQMPfMEjQyA%253D%253D%26midToken%3DAQFc9IHrfZn0mw%26midSig%3D2OAN1ppLQoHqY1%26trk%3Deml-email_m2m_invite_single_01-invitation~card-0-accept~invite%26trkEmail%3Deml-email_m2m_invite_single_01-invitation~card-0-accept~invite-null-djvn5m~lns7v3ou~9a-null-null%26eid%3Ddjvn5m-lns7v3ou-9a%26otpToken%3DMWEwNDE3ZTQxNjI3Y2FjN2JjMjQwNGVjNGYxOWUzYjU4NmM5ZDA0MDlhYTY4ZjYxNzljNzA0NmI0NjUyNWVmN2YwZGRkZmI1NDhiOGNkZmEwNmI5ZjZhNDVhNjc0YzY3OWIwZTkxOWQyODJhNGJlNTM3Yzg1OCwxLDE%253D&amp;source=gmail&amp;ust=1697549047316000&amp;usg=AOvVaw2Yhh2iDGsjeDI-xryg-9h-\">                                                         <span style=\"color:rgb(255,255,255)\">Aceptar</span>                                                       </a>                                                     </td>                                                   </tr>                                                 </tbody>                                               </table>                                             </a>                                           </td>                                         </tr>                                       </tbody>                                     </table>                                   </td>                                   <td style=\"padding-left:8px\">                                     <table role=\"presentation\" valign=\"top\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\">                                       <tbody>                                         <tr>                                           <td valign=\"middle\" align=\"middle\">                                             <a href=\"https://belydu.antoniosaborido.es/login/u/linkedin\" aria-label=\"Ver perfil de Jorge H.\" style=\"color:rgb(10,102,194);display:inline-block;text-decoration-line:none;vertical-align:top\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=https://www.linkedin.com/comm/in/jorge-h-418b14212?lipi%3Durn%253Ali%253Apage%253Aemail_email_m2m_invite_single_01%253BjsI0FYh5T8yAQMPfMEjQyA%253D%253D%26midToken%3DAQFc9IHrfZn0mw%26midSig%3D2OAN1ppLQoHqY1%26trk%3Deml-email_m2m_invite_single_01-invitation~card-0-view~profile%26trkEmail%3Deml-email_m2m_invite_single_01-invitation~card-0-view~profile-null-djvn5m~lns7v3ou~9a-null-null%26eid%3Ddjvn5m-lns7v3ou-9a%26otpToken%3DMWEwNDE3ZTQxNjI3Y2FjN2JjMjQwNGVjNGYxOWUzYjU4NmM5ZDA0MDlhYTY4ZjYxNzljNzA0NmI0NjUyNWVmN2YwZGRkZmI1NDhiOGNkZmEwNmI5ZjZhNDVhNjc0YzY3OWIwZTkxOWQyODJhNGJlNTM3Yzg1OCwxLDE%253D&amp;source=gmail&amp;ust=1697549047316000&amp;usg=AOvVaw1ZKrvEgSd7MltzZ4VewxD_\">                                               <table role=\"presentation\" valign=\"top\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"auto\">                                                 <tbody>                                                   <tr>                                                     <td style=\"height:min-content;border-radius:24px;padding:12px 24px;text-align:center;font-size:16px;font-weight:600;background-color:rgba(0,0,0,0);border-width:1px;border-style:solid;border-color:rgb(10,102,194);line-height:1.25;min-height:auto\">                                                       <a href=\"https://belydu.antoniosaborido.es/login/u/linkedin\" aria-hidden=\"true\" style=\"color:rgb(10,102,194);display:inline-block;text-decoration-line:none\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=https://www.linkedin.com/comm/in/jorge-h-418b14212?lipi%3Durn%253Ali%253Apage%253Aemail_email_m2m_invite_single_01%253BjsI0FYh5T8yAQMPfMEjQyA%253D%253D%26midToken%3DAQFc9IHrfZn0mw%26midSig%3D2OAN1ppLQoHqY1%26trk%3Deml-email_m2m_invite_single_01-invitation~card-0-view~profile%26trkEmail%3Deml-email_m2m_invite_single_01-invitation~card-0-view~profile-null-djvn5m~lns7v3ou~9a-null-null%26eid%3Ddjvn5m-lns7v3ou-9a%26otpToken%3DMWEwNDE3ZTQxNjI3Y2FjN2JjMjQwNGVjNGYxOWUzYjU4NmM5ZDA0MDlhYTY4ZjYxNzljNzA0NmI0NjUyNWVmN2YwZGRkZmI1NDhiOGNkZmEwNmI5ZjZhNDVhNjc0YzY3OWIwZTkxOWQyODJhNGJlNTM3Yzg1OCwxLDE%253D&amp;source=gmail&amp;ust=1697549047316000&amp;usg=AOvVaw1ZKrvEgSd7MltzZ4VewxD_\">Ver perfil</a>                                                     </td>                                                   </tr>                                                 </tbody>                                               </table>                                             </a>                                           </td>                                         </tr>                                       </tbody>                                     </table>                                   </td>                                 </tr>                               </tbody>                             </table>                           </td>                         </tr>                       </tbody>                     </table>                   </td>                 </tr>                 <tr>                   <td style=\"padding-top:24px\">                     <table role=\"presentation\" valign=\"top\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\">                       <tbody>                         <tr>                           <td style=\"padding-bottom:8px\">                             <h2 style=\"margin:0px;text-align:center;font-size:20px;line-height:1.25;color:rgb(40,40,40)\">                               <br>                             </h2>                           </td>                         </tr>                         <tr>                           <td style=\"padding-top:8px\">                             <table role=\"presentation\" valign=\"top\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\">                               <tbody></tbody>                             </table>                           </td>                         </tr>                       </tbody>                     </table>                   </td>                 </tr>               </tbody>             </table>           </td>         </tr>         <tr>           <td style=\"background-color:rgb(243,242,240);padding:24px\">             <table role=\"presentation\" valign=\"top\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\">               <tbody>                 <tr>                   <td>                     <br>                   </td>                 </tr>               </tbody>             </table>             <table role=\"presentation\" valign=\"top\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" style=\"font-size:12px\">               <tbody>                 <tr>                   <td style=\"padding-bottom:8px\">                     <a href=\"https://belydu.antoniosaborido.es/login/u/linkedin\" style=\"color:rgb(10,102,194);display:inline-block;text-decoration-line:none\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=https://www.linkedin.com/comm/feed/?lipi%3Durn%253Ali%253Apage%253Aemail_email_m2m_invite_single_01%253BjsI0FYh5T8yAQMPfMEjQyA%253D%253D%26midToken%3DAQFc9IHrfZn0mw%26midSig%3D2OAN1ppLQoHqY1%26trk%3Deml-email_m2m_invite_single_01-footer-0-logoGlimmer%26trkEmail%3Deml-email_m2m_invite_single_01-footer-0-logoGlimmer-null-djvn5m~lns7v3ou~9a-null-null%26eid%3Ddjvn5m-lns7v3ou-9a%26otpToken%3DMWEwNDE3ZTQxNjI3Y2FjN2JjMjQwNGVjNGYxOWUzYjU4NmM5ZDA0MDlhYTY4ZjYxNzljNzA0NmI0NjUyNWVmN2YwZGRkZmI1NDhiOGNkZmEwNmI5ZjZhNDVhNjc0YzY3OWIwZTkxOWQyODJhNGJlNTM3Yzg1OCwxLDE%253D&amp;source=gmail&amp;ust=1697549047316000&amp;usg=AOvVaw36i7Daxy4B-HRJbL0Ey5WF\">                       <img src=\"https://ci6.googleusercontent.com/proxy/-jwG3nQtaq8_mbFKsdGI8Va5EsLlnYDqdMRaGfxPINQzoEeWTnHCIttjbxwuZMCo_c-yBRe6tyfDfKAWPe2KgeCTytCd-tyvqa9IFHn05-vnMQ=s0-d-e1-ft#https://static.licdn.com/aero-v1/sc/h/9ehe6n39fa07dc5edzv0rla4e\" alt=\"LinkedIn\" width=\"56\" height=\"14\" style=\"outline:none;display:block;height:14px;width:56px\" class=\"CToWUd\" data-bit=\"iit\">                     </a>                   </td>                 </tr>                 <tr>                   <td>© 2023&nbsp;LinkedIn&nbsp;Ireland Unlimited Company, Wilton Plaza, Wilton Place, Dublín 2.&nbsp;LinkedIn&nbsp;es un nombre comercial registrado de&nbsp;LinkedIn&nbsp;Ireland Unlimited Company.&nbsp;LinkedIn&nbsp;y el logotipo de&nbsp;LinkedIn&nbsp;son marcas registradas de&nbsp;LinkedIn.</td>                 </tr>               </tbody>             </table>           </td>         </tr>       </tbody>     </table>     <div class=\"yj6qo\"></div>     <div class=\"adL\">       <br>     </div>   </div> </div>";

        try {
            emailService.sendEmail2(to, subject, htmlContent);
            return "Email sent successfully";
        } catch (MessagingException e) {
            return "Failed to send email: " + e.getMessage();
        }
    }
}