package com.klu.homestay.service;

import com.klu.homestay.model.Hotel;

import java.util.List;

public interface HotelService {
    List<Hotel> getAllHotels();
    Hotel getHotelById(Long id);
    Hotel saveHotel(Hotel hotel);
    Hotel updateHotel(Long id, Hotel hotel);
    boolean deleteHotel(Long id);
}
