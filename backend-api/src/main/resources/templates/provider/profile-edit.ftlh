<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PetPal - Edit Profile</title>
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
                    <a href="/provider/${providerId}/dashboard/${providerId}"
                        class="text-sm font-semibold text-black hover:text-black transition-colors">Dashboard</a>
                    <a href="/provider/${providerId}/customer-list"
                        class="text-sm font-semibold text-black hover:text-black transition-colors">Customers</a>
                    <a href="/" class="text-sm font-semibold text-black hover:text-black transition-colors">Logout</a>
                </nav>
            </div>
    </header>

    <main class="max-w-4xl mx-auto px-6 py-10 space-y-6">

        <!-- EDIT PROFILE -->
        <section class="bg-white border border-gray-200 rounded-xl shadow-sm p-8">
            <h3 class="text-xs text-center text-gray-500 uppercase tracking-widest font-semibold mb-6">
                Edit Profile
            </h3>
            <form method="post" action="/provider/${provider.id}/edit" class="space-y-6">
                <div class="flex flex-col sm:flex-row gap-6 items-start">
                    <div class="flex-1 w-full">
                        <div class="space-y-6">
                            <div class="flex flex-col sm:flex-row gap-6 items-start">
                                <div class="flex-1 w-full">
                                    <label
                                        class="block text-xs text-gray-500 uppercase tracking-widest font-semibold mb-2 mt-8">
                                        Profile Image Link
                                    </label>
                                    <input type="text" name="imageUrl" required placeholder="Paste an image URL" value="${(imageUrl)!}"
                                        class="w-full px-6 py-4 text-sm border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-black focus:border-transparent transition-all">
                                    <#if imageUrlError??>
                                        <div class="mb-4 px-4 py-3 rounded-lg bg-red-50 border border-red-200 text-red-700 text-sm font-medium">
                                            ${naimageUrlErrormeError}
                                        </div>
                                    </#if>
                                </div>
                            </div>
                            <div>
                                <label
                                    class="block text-xs text-gray-500 uppercase tracking-widest font-semibold mb-2 mt-8">
                                    Name
                                </label>
                                <input type="text" name="name" placeholder="John Doe" value="${(name)!}" required
                                    class="w-full px-6 py-4 text-sm border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-black focus:border-transparent transition-all">
                                <#if nameError??>
                                    <div class="mb-4 px-4 py-3 rounded-lg bg-red-50 border border-red-200 text-red-700 text-sm font-medium">
                                        ${nameError}
                                    </div>
                                </#if>
                            </div>
                            <div>
                                <label
                                    class="block text-xs text-gray-500 uppercase tracking-widest font-semibold mb-2 mt-8">
                                    Email
                                </label>
                                <input type="text" name="email" placeholder="email@provider.com" value="${(email)!}" required
                                    class="w-full px-6 py-4 text-sm border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-black focus:border-transparent transition-all">
                                <#if emailError??>
                                    <div class="mb-4 px-4 py-3 rounded-lg bg-red-50 border border-red-200 text-red-700 text-sm font-medium">
                                        ${emailError}
                                    </div>
                                </#if>
                            </div>
                            <div>
                                <label
                                    class="block text-xs text-gray-500 uppercase tracking-widest font-semibold mb-2 mt-8">
                                    Password (must be 8 characters)
                                </label>
                                <input type="password" name="password" placeholder="123password" required
                                    class="w-full px-6 py-4 text-sm border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-black focus:border-transparent transition-all">
                                <#if passwordError??>
                                    <div class="mb-4 px-4 py-3 rounded-lg bg-red-50 border border-red-200 text-red-700 text-sm font-medium">
                                        ${passwordError}
                                    </div>
                                </#if>
                            </div>
                            <div>
                                <label
                                    class="block text-xs text-gray-500 uppercase tracking-widest font-semibold mb-2 mt-8">
                                    Phone Number
                                </label>
                                <input type="text" name="phone" placeholder="(123) 456-7890" value="${(phone)!}"
                                    required
                                    class="w-full px-6 py-4 text-sm border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-black focus:border-transparent transition-all">
                                <#if phoneError??>
                                    <div class="mb-4 px-4 py-3 rounded-lg bg-red-50 border border-red-200 text-red-700 text-sm font-medium">
                                        ${phoneError}
                                    </div>
                                </#if>
                            </div>
                            <div>
                                <label
                                    class="block text-xs text-gray-500 uppercase tracking-widest font-semibold mb-2 mt-8">
                                    Address
                                </label>
                                <input type="text" name="address" placeholder="City, ST" value="${(address)!}"
                                    required
                                    class="w-full px-6 py-4 text-sm border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-black focus:border-transparent transition-all">
                                <#if addressError??>
                                    <div class="mb-4 px-4 py-3 rounded-lg bg-red-50 border border-red-200 text-red-700 text-sm font-medium">
                                        ${addressError}
                                    </div>
                                </#if>
                            </div>
                            <div>
                                <label
                                    class="block text-xs text-gray-500 uppercase tracking-widest font-semibold mb-2 mt-8">
                                    Description
                                </label>
                                <textarea rows="8" placeholder="What's up?" required name="description"
                                    class="w-full px-6 py-4 text-sm border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-black focus:border-transparent transition-all">
${(description)!}
                                </textarea>
                            </div>

                            <div class="flex gap-4">
                                <a href="/provider/${provider.id}/dashboard/"
                                    class="flex justify-center flex-1 px-6 py-4 mt-8 text-white bg-black rounded-lg hover:bg-gray-500 transition-colors font-medium shadow-sm">
                                    Cancel
                                </a>
                                <button type="submit"
                                    class="flex justify-center flex-1 px-6 py-4 mt-8 text-white bg-black rounded-lg hover:bg-gray-500 transition-colors font-medium shadow-sm">
                                    Save
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </section>

        <!-- DELETE ACCOUNT BUTTON  -->
        <div class="flex justify-center">
            <a href="/provider/${providerId}/dashboard/"
                class="flex justify-center px-6 py-4 text-red bg-white border-2 border-red rounded-lg hover:bg-red-500 transition-colors font-medium">
                Delete Account
            </a>
        </div>

    </main>

    <!-- save page position on reload -->
    <script>
        document.querySelectorAll('form').forEach(form => {
            form.addEventListener('submit', () => {
                sessionStorage.setItem('scrollY', window.scrollY);
            });
        });

        window.addEventListener('DOMContentLoaded', () => {
            const y = sessionStorage.getItem('scrollY');
            if (y !== null && window.location.hash === '#scrolly') {
                window.scrollTo(0, parseInt(y, 10));
                sessionStorage.removeItem('scrollY');
            }
        })
    </script>
</body>

</html>