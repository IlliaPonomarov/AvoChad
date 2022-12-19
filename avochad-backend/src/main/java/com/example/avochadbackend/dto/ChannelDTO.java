package com.example.avochadbackend.dto;

public class ChannelDTO {

    private String title;
    private Long organizationId;
    private Long chatId;

    public ChannelDTO() {
    }

    public ChannelDTO(String title, Long organizationId, Long chatId) {
        this.title = title;
        this.organizationId = organizationId;
        this.chatId = chatId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }


}
