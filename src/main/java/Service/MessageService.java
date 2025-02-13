package Service;


import Model.Message;
import DAO.MessageDAO;
import java.util.List;

public class MessageService {
    private MessageDAO messageDAO;
    
    public MessageService(){
        messageDAO = new MessageDAO();
    }

    public MessageService (MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }
    public Message addMessage(Message message){
        return messageDAO.addMessage(message);
    }
    public List<Message> getAllMessages(){
        return messageDAO.getAllMessages();
    }
    public Message getMessageById(int message_id){
        return messageDAO.getMessageById(message_id);
    }
    public Message deleteMessageById(int message_id){
        Message message = messageDAO.getMessageById(message_id);
        messageDAO.deleteMessageById(message_id);
        return message;
    }
    public Message updateMessageById(String message_text, int message_id){
        Boolean updated = messageDAO.updateMessageById(message_text, message_id);
        if(updated){
        System.out.println(updated);
        return messageDAO.getMessageById(message_id);
        }
        System.out.println(updated);
        return null;
    }
    public List<Message> getMessagesByUser(int user_id){
        return messageDAO.getMessagesByUser(user_id);
    }
}
