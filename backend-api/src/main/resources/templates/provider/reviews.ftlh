<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PetPal = Respond Reviews</title>
    <!--tailwind setup-->
    <script src="https://cdn.tailwindcss.com"></script>
</head>


<body class="bg-gray-50 min-h-screen font-sans text-black">

    <!-- SITE HEADER -->
    <header>
        <div class="bg-white border-b border-gray-200">
            <div class="max-w-4xl mx-auto px-6 py-4 flex items-center">
                <h1 class="text-2xl font-extrabold tracking-tight">PetPal</h1>
                <nav class="ml-auto flex items-center gap-6">
                    <a href="/provider/${providerId}/dashboard"
                        class="text-sm font-semibold text-black hover:text-black transition-colors">Dashboard</a>
                    <a href="/provider/${providerId}/customer-list"
                        class="text-sm font-semibold text-black hover:text-black transition-colors">Customers</a>
                    <a href="/" class="text-sm font-semibold text-black hover:text-black transition-colors">Logout</a>
                </nav>
            </div>
    </header>

    <main class="max-w-4xl mx-auto px-6 py-10 space-y-6">

        <!-- RATINGS SUMMARY -->
        <section class="bg-white border border-gray-200 rounded-xl shadow-sm p-8">
            <h3 class="text-xs text-center text-gray-500 uppercase tracking-widest font-semibold mb-2">
                My ratings
            </h3>
            <p class="text-center text-2xl font-extrabold tracking-tight">Reviews</p>
        </section>

        <!-- REVIEWS -->
        <section class="bg-white border border-gray-200 rounded-xl shadow-sm p-8">
            <h3 class="text-xs text-center text-gray-500 uppercase tracking-widest font-semibold mb-6">
                Reviews
            </h3>

            <div class="space-y-6">

                <#list reviews as review>
                    <article class="border border-gray-200 rounded-lg p-8">
                        <div class="flex items-center justify-between">
                            <img src="" alt="Profile picture"
                                class="w-16 h-16 rounded-full object-cover border border-gray-200 flex-shrink-0">
                            <a href="/provider/${provider.id}/customer-profile/${review.customer.id}"
                                class="text-sm font-semibold">${review.customer.name}</a>
                            <p class="text-xs text-gray-500">${review.formattedCreatedAt}</p>
                        </div>
                        <p class="text-sm text-gray-500 mt-2">
                            <#if review.recommended>
                                👍 Liked
                                <#else>
                                    👎 Disliked
                            </#if>
                        </p>
                        <p class="text-sm text-gray-500 mt-3">
                            ${review.customerComment}
                        </p>

                        <#if review.hasResponse>

                            <div class="mt-5 border-t border-gray-200 pt-5">
                                <label class="block text-xs text-gray-500 uppercase tracking-widest font-semibold mb-2">
                                    Your Response
                                </label>
                                <p class="text-sm text-gray-500 bg-gray-50 border border-gray-200 rounded-lg px-6 py-4">
                                    ${review.providerResponse}
                                </p>
                            </div>

                            <#else>

                                <div class="mt-5 border-t border-gray-200 pt-5">
                                    <label
                                        class="block text-xs text-gray-500 uppercase tracking-widest font-semibold mb-2">
                                        Your Response
                                    </label>

                                    <form action="/provider/${provider.id}/review/${review.id}/create-response"
                                        method="post">
                                        <textarea rows="3" placeholder="Write a response..." name="providerResponse"
                                            class="w-full px-6 py-4 text-sm border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-black focus:border-transparent transition-all"></textarea>
                                        <button type="submit"
                                            class="flex justify-center mt-3 px-6 py-4 text-white bg-black rounded-lg hover:bg-gray-500 transition-colors font-medium shadow-sm">
                                            Post Response
                                        </button>
                                    </form>
                                </div>
                        </#if>
                    </article>
                </#list>

            </div>
        </section>

        </div>
        </section>

        <!-- BACK BUTTON -->
        <div class="flex justify-center">
            <a href="/provider/${providerId}/dashboard"
                class="flex justify-center px-6 py-4 text-black bg-white border-2 border-black rounded-lg hover:bg-gray-500 transition-colors font-medium">
                Back to Dashboard
            </a>
        </div>

    </main>
</body>

</html>