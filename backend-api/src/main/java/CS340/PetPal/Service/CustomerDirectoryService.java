package CS340.PetPal.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import CS340.PetPal.Entity.Job;
import CS340.PetPal.Entity.Provider;
import CS340.PetPal.Entity.Review;
import CS340.PetPal.Repository.ProviderRepository;

@Service
public class CustomerDirectoryService {

    private final ProviderRepository providerRepository;

    public CustomerDirectoryService(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    @Transactional(readOnly = true)
    public List<Provider> findProviders(String keyword, String location, String service, Double maxRate,
            String sort) {
        List<Provider> matches = new ArrayList<>();

        for (Provider provider : providerRepository.findAll()) {
            if (matchesKeyword(provider, keyword)
                    && contains(provider.getAddress(), location)
                    && offersService(provider, service)
                    && hasRateAtOrBelow(provider, maxRate)) {
                matches.add(provider);
            }
        }

        sortProviders(matches, sort);
        return matches;
    }

    private boolean matchesKeyword(Provider provider, String keyword) {
        return isBlank(keyword)
                || contains(provider.getName(), keyword)
                || contains(provider.getDescription(), keyword);
    }

    private boolean offersService(Provider provider, String service) {
        if (isBlank(service)) {
            return true;
        }

        for (Job job : provider.getJobs()) {
            if (contains(job.getName(), service)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasRateAtOrBelow(Provider provider, Double maxRate) {
        if (maxRate == null) {
            return true;
        }

        for (Job job : provider.getJobs()) {
            if (job.getPrice() <= maxRate) {
                return true;
            }
        }
        return false;
    }

    private void sortProviders(List<Provider> providers, String sort) {
        if ("rating".equals(sort)) {
            providers.sort(Comparator.comparingDouble(this::recommendationRate).reversed());
        } else if ("price".equals(sort)) {
            providers.sort(Comparator.comparingDouble(this::startingRate));
        } else {
            providers.sort(Comparator.comparingInt((Provider provider) -> provider.getReviews().size()).reversed());
        }
    }

    private double recommendationRate(Provider provider) {
        if (provider.getReviews().isEmpty()) {
            return 0;
        }

        int recommendations = 0;
        for (Review review : provider.getReviews()) {
            if (Boolean.TRUE.equals(review.getRecommended())) {
                recommendations++;
            }
        }
        return (double) recommendations / provider.getReviews().size();
    }

    private double startingRate(Provider provider) {
        double lowestRate = Double.MAX_VALUE;
        for (Job job : provider.getJobs()) {
            if (job.getPrice() < lowestRate) {
                lowestRate = job.getPrice();
            }
        }
        return lowestRate;
    }

    private boolean contains(String value, String search) {
        return isBlank(search)
                || value != null && value.toLowerCase().contains(search.trim().toLowerCase());
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
