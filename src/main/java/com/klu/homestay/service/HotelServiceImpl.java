package com.klu.homestay.service;

import com.klu.homestay.model.Hotel;
import com.klu.homestay.repository.HotelRepository;
import com.klu.homestay.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotelById(Long id) {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        return hotel.orElse(null);
    }

    @Override
    public Hotel saveHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel updateHotel(Long id, Hotel hotelDetails) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (optionalHotel.isPresent()) {
            Hotel existingHotel = optionalHotel.get();
            existingHotel.setName(hotelDetails.getName());
            existingHotel.setAddress(hotelDetails.getAddress());
            existingHotel.setCity(hotelDetails.getCity());
            existingHotel.setState(hotelDetails.getState());
            existingHotel.setCountry(hotelDetails.getCountry());
            existingHotel.setDescription(hotelDetails.getDescription());
            existingHotel.setStarRating(hotelDetails.getStarRating());
            existingHotel.setAmenities(hotelDetails.getAmenities());
            existingHotel.setPhone(hotelDetails.getPhone());
            existingHotel.setEmail(hotelDetails.getEmail());
            existingHotel.setWebsite(hotelDetails.getWebsite());
            return hotelRepository.save(existingHotel);
        }
        return null;
    }

    @Override
    public boolean deleteHotel(Long id) {
        if (hotelRepository.existsById(id)) {
            hotelRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
